package org.fluffy.pet.rms.resourcemanagement.configuration;

import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;
import org.fluffy.pet.rms.resourcemanagement.model.service.FluffyPetService;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.CompoundIndexDefinition;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.PartialIndexFilter;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

@Configuration
public class MongoIndexConfiguration {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoIndexConfiguration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private <T> void createIndexForEntity(Class<T> entityClass, List<Document> indexFields) {
        IndexOperations indexOperations = mongoTemplate.indexOps(entityClass);

        PartialIndexFilter statusPartialIndexFilter = PartialIndexFilter.of(
                Criteria.where(MongoConstants.STATUS).in(Status.ACTIVE)
        );

        List<Index> indices = new java.util.ArrayList<>(
                indexFields.stream()
                        .map(
                                indexField -> new CompoundIndexDefinition(indexField)
                                        .partial(statusPartialIndexFilter)
                                        .unique()
                        )
                        .toList()
        );
        indices.forEach(indexOperations::ensureIndex);
    }

    @PostConstruct
    public void createPetIndex() {
        Document document = new Document();
        document.put(MongoConstants.NAME, 1);
        document.put(String.format("%s.%s", MongoConstants.OWNER, MongoConstants.USER_ID), 1);

        createIndexForEntity(Pet.class, List.of(document));
    }

    @PostConstruct
    public void createServiceIndex() {
        Document document = new Document();
        document.put(MongoConstants.SERVICE_SUB_TYPE, 1);
        document.put(String.format("%s.%s", MongoConstants.PROVIDER, MongoConstants.PROVIDER_ID), 1);

        createIndexForEntity(FluffyPetService.class, List.of(document));
    }

    @PostConstruct
    public void createUserIndex() {
        Document document = new Document();
        document.put(MongoConstants.MOBILE, 1);

        Document document1 = new Document();
        document1.put(MongoConstants.EMAIL, 1);

        createIndexForEntity(Pet.class, List.of(document, document1));
    }
}
