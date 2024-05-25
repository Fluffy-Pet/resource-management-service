package org.fluffy.pet.rms.resourcemanagement.repository.common.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.*;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.repository.common.CommonRepository;
import org.fluffy.pet.rms.resourcemanagement.util.DynamoConstants;

import java.util.*;
import java.util.stream.Collectors;

public class CommonRepositoryImpl<T, ID> implements CommonRepository<T, ID> {
    private final DynamoDBMapper dynamoDBMapper;

    private final AmazonDynamoDB amazonDynamoDB;

    private final Class<T> entityClass;

    private final DynamoDBTable table;

    public CommonRepositoryImpl(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB amazonDynamoDB, Class<T> entityClass) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.amazonDynamoDB = amazonDynamoDB;
        this.entityClass = entityClass;
        this.table = entityClass.getAnnotation(DynamoDBTable.class);
    }

    @Override
    public void save(T entity) {
        dynamoDBMapper.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        DynamoDBQueryExpression<T> dynamoDBQueryExpression = new DynamoDBQueryExpression<>();
        dynamoDBQueryExpression = dynamoDBQueryExpression.addExpressionAttributeValuesEntry(
                DynamoConstants.ID,
                new AttributeValue().withS(id.toString())
        );
        dynamoDBQueryExpression = dynamoDBQueryExpression.addExpressionAttributeValuesEntry(
                DynamoConstants.STATUS,
                new AttributeValue().withS(Status.ACTIVE.name())
        );
        QueryResultPage<T> queryResultPage = dynamoDBMapper.queryPage(entityClass, dynamoDBQueryExpression);
        List<T> results = queryResultPage.getResults();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
    }

    @Override
    // This has no optimistic lock implemented
    public boolean deleteById(ID id) {
        HashMap<String, AttributeValue> itemKey = new HashMap<>();
        itemKey.put(DynamoConstants.ID, new AttributeValue().withS(id.toString()));
        itemKey.put(DynamoConstants.STATUS, new AttributeValue().withS(Status.ACTIVE.name()));

        HashMap<String, AttributeValueUpdate> updatedValues = new HashMap<>();
        updatedValues.put(
                DynamoConstants.STATUS,
                new AttributeValueUpdate().withValue(
                        new AttributeValue().withS(Status.INACTIVE.name())
                ).withAction(AttributeAction.PUT)
        );

        UpdateItemRequest updateItemRequest = new UpdateItemRequest();
        updateItemRequest.setTableName(table.tableName());
        updateItemRequest.setKey(itemKey);
        updateItemRequest.setAttributeUpdates(updatedValues);

        UpdateItemResult updateItemResult = amazonDynamoDB.updateItem(updateItemRequest);
        ConsumedCapacity consumedCapacity = updateItemResult.getConsumedCapacity();
        Double capacityUnits = consumedCapacity.getCapacityUnits();
        return capacityUnits != null && capacityUnits > 0;
    }

    @Override
    public List<T> findAllByIds(Iterable<ID> ids) {
        List<Map<String, AttributeValue>> keys = new ArrayList<>();
        ids.forEach(id -> keys.add(Map.of(DynamoConstants.ID, new AttributeValue().withS(id.toString()))));

        Map<String, KeysAndAttributes> requestItems = new HashMap<>();
        requestItems.put(table.tableName(), new KeysAndAttributes().withKeys(keys));

        BatchGetItemRequest batchGetItemRequest = new BatchGetItemRequest().withRequestItems(requestItems);
        BatchGetItemResult result = amazonDynamoDB.batchGetItem(batchGetItemRequest);

        List<Map<String, AttributeValue>> responses = result.getResponses().get(table.tableName());
        return responses.stream()
                .map(item -> dynamoDBMapper.marshallIntoObject(entityClass, item))
                .collect(Collectors.toList());
    }
}
