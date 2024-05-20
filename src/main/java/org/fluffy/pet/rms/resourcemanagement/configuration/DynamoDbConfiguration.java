package org.fluffy.pet.rms.resourcemanagement.configuration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories("org.fluffy.pet.rms.resourcemanagement.repository")
public class DynamoDbConfiguration {
    @Bean
    public AmazonDynamoDB dynamoDbClient() {
        return AmazonDynamoDBClient
                .builder()
                .build();
    }
}
