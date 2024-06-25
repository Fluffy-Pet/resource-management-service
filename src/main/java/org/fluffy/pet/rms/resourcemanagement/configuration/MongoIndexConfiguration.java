package org.fluffy.pet.rms.resourcemanagement.configuration;

import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.model.animal.Pet;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.CompoundIndexDefinition;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.PartialIndexFilter;
import org.springframework.data.mongodb.core.query.Collation;
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

        Collation collation = Collation.of("en")
                .strength(Collation.ComparisonLevel.secondary());

        PartialIndexFilter statusPartialIndexFilter = PartialIndexFilter.of(
                Criteria.where(MongoConstants.STATUS).in(Status.ACTIVE)
        );

        List<Index> indices = new java.util.ArrayList<>(
                indexFields.stream()
                        .map(
                                indexField -> new CompoundIndexDefinition(indexField).collation(collation)
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
}
