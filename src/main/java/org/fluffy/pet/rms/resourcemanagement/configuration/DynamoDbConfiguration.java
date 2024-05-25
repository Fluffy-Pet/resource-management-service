package org.fluffy.pet.rms.resourcemanagement.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.*;
import org.fluffy.pet.rms.resourcemanagement.annotations.DynamoDBTableConfig;
import org.fluffy.pet.rms.resourcemanagement.annotations.GlobalSecondaryIndexAnnotation;
import org.fluffy.pet.rms.resourcemanagement.configuration.properties.AwsProperties;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;
import org.fluffy.pet.rms.resourcemanagement.util.StreamUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.socialsignin.spring.data.dynamodb.core.DynamoDBOperations;
import org.socialsignin.spring.data.dynamodb.core.DynamoDBTemplate;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
@EnableDynamoDBRepositories("org.fluffy.pet.rms.resourcemanagement.repository")
public class DynamoDbConfiguration {
    @Bean
    public AmazonDynamoDB dynamoDbClient(@Autowired AwsProperties awsProperties) {
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
                awsProperties.getDynamoDbEndPoint(),
                awsProperties.getRegion()
        );
        return AmazonDynamoDBClient
                .builder()
                .withCredentials(new AWSStaticCredentialsProvider(getAwsCredentials(awsProperties)))
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

    @Bean
    public AWSCredentials getAwsCredentials(@Autowired AwsProperties awsProperties) {
        return new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey());
    }

    @Bean
    public DynamoDBOperations dynamoDBOperations(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB) {
        DynamoDBTemplate dynamoDBTemplate = new DynamoDBTemplate(
                amazonDynamoDB,
                dynamoDBMapper,
                DynamoDBMapperConfig.DEFAULT
        );

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(DynamoConstants.DEFAULT_DB_MODEL_PACKAGE))
                .setScanners(Scanners.TypesAnnotated)
                .filterInputsBy(new FilterBuilder().includePackage(DynamoConstants.DEFAULT_DB_MODEL_PACKAGE)));

        Set<Class<?>> dynamoDbTables = reflections.getTypesAnnotatedWith(DynamoDBTable.class);

        for (Class<?> clazz : dynamoDbTables) {
            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(clazz);
            tableRequest.setProvisionedThroughput(
                    new ProvisionedThroughput(
                            DynamoConstants.READ_CAPACITY_DEFAULT_UNITS,
                            DynamoConstants.WRITE_CAPACITY_DEFAULT_UNITS
                    )
            );

            DynamoDBTableConfig tableConfig = clazz.getAnnotation(DynamoDBTableConfig.class);
            if (tableConfig != null) {
                List<GlobalSecondaryIndex> globalSecondaryIndices = new ArrayList<>();
                for (GlobalSecondaryIndexAnnotation gsiAnnotation : tableConfig.globalSecondaryIndexes()) {
                    List<KeySchemaElement> keySchemaElements = new ArrayList<>();
                    keySchemaElements.add(new KeySchemaElement(gsiAnnotation.hashKey(), KeyType.HASH));
                    if (!gsiAnnotation.rangeKey().isEmpty()) {
                        keySchemaElements.add(new KeySchemaElement(gsiAnnotation.rangeKey(), KeyType.RANGE));
                    }
                    GlobalSecondaryIndex gsi = new GlobalSecondaryIndex()
                            .withIndexName(gsiAnnotation.indexName())
                            .withKeySchema(keySchemaElements)
                            .withProjection(new Projection().withProjectionType(ProjectionType.ALL))
                            .withProvisionedThroughput(
                                    new ProvisionedThroughput(
                                            DynamoConstants.READ_CAPACITY_DEFAULT_UNITS,
                                            DynamoConstants.WRITE_CAPACITY_DEFAULT_UNITS
                                    )
                            );

                    globalSecondaryIndices.add(gsi);
                }
                tableRequest.withGlobalSecondaryIndexes(globalSecondaryIndices);

                if (!amazonDynamoDB.listTables().getTableNames().contains(tableRequest.getTableName())) {
                    amazonDynamoDB.createTable(tableRequest);
                } else {
                    TableDescription tableDescription = amazonDynamoDB.describeTable(tableRequest.getTableName()).getTable();
                    List<GlobalSecondaryIndexDescription> existingIndexes = tableDescription.getGlobalSecondaryIndexes();
                    for (GlobalSecondaryIndex gsi : globalSecondaryIndices) {
                        boolean indexExists = StreamUtils.emptyIfNull(existingIndexes).anyMatch(index -> index.getIndexName().equals(gsi.getIndexName()));

                        if (!indexExists) {
                            UpdateTableRequest updateTableRequest = new UpdateTableRequest()
                                    .withTableName(tableRequest.getTableName())
                                    .withGlobalSecondaryIndexUpdates(new GlobalSecondaryIndexUpdate()
                                            .withCreate(new CreateGlobalSecondaryIndexAction()
                                                    .withIndexName(gsi.getIndexName())
                                                    .withKeySchema(gsi.getKeySchema())
                                                    .withProjection(gsi.getProjection())
                                                    .withProvisionedThroughput(gsi.getProvisionedThroughput())));

                            amazonDynamoDB.updateTable(updateTableRequest);
                        }
                    }
                }
            }
        }
        return dynamoDBTemplate;
    }
}
