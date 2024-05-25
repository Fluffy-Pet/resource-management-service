package org.fluffy.pet.rms.resourcemanagement.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.fluffy.pet.rms.resourcemanagement.configuration.properties.AwsProperties;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public AWSCredentials getAwsCredentials(@Autowired AwsProperties awsProperties) {
        return new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey());
    }
}
