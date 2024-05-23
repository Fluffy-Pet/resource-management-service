package config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.fluffy.pet.rms.resourcemanagement.repository.ClinicRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.DoctorRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.InfrastructureRepository;
import org.fluffy.pet.rms.resourcemanagement.repository.VolunteerRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.repository")
public class DynamoDBConfig {

    @Value("${aws.dynamodb.endpoint}")
    private String dynamoDbEndpoint;

    @Value("${aws.region}")
    private String region;

    @Bean
    @Primary
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDbEndpoint, region))
                .build();
    }
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    }

    @Bean
    public DoctorRepository doctorRepository() {
        return new DoctorRepository(dynamoDBMapper(), amazonDynamoDB());
    }

    @Bean
    public VolunteerRepository volunteerRepository() {
        return new VolunteerRepository(dynamoDBMapper(), amazonDynamoDB());
    }

    @Bean
    public ClinicRepository clinicRepository() {
        return new ClinicRepository(dynamoDBMapper(), amazonDynamoDB());
    }

    @Bean
    public InfrastructureRepository infrastructureRepository() {
        return new InfrastructureRepository(dynamoDBMapper(), amazonDynamoDB());
    }
}
