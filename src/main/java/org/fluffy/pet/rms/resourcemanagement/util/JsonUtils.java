package org.fluffy.pet.rms.resourcemanagement.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;

import java.util.Set;

public final class JsonUtils {

    public static JsonNode extractResultFromJsonNode(
            JsonNode rootNode,
            Set<String> fields,
            ObjectMapper objectMapper
    ) throws AppException {
        ObjectNode resultNode = objectMapper.createObjectNode();

        fields.forEach(field -> {
            String[] parts = field.split(Constants.FILTER_SEPARATOR);
            addFieldToResult(rootNode, resultNode, parts, 0, field, objectMapper);
        });

        return resultNode;
    }

    private static void addFieldToResult(
            JsonNode sourceNode,
            ObjectNode resultNode,
            String[] parts,
            int index,
            String fullPath,
            ObjectMapper objectMapper
    ) {
        String currentPart = parts[index];

        if (sourceNode.has(currentPart)) {
            if (index == parts.length - 1) {
                JsonNode targetNode = sourceNode.get(currentPart);
                if (targetNode.isArray()) {
                    ArrayNode arrayNode = objectMapper.createArrayNode();
                    for (JsonNode element : targetNode) {
                        arrayNode.add(element);
                    }
                    resultNode.set(fullPath, arrayNode);
                } else {
                    resultNode.set(fullPath, targetNode);
                }
            } else {
                JsonNode nextNode = sourceNode.get(currentPart);
                if (nextNode.isObject()) {
                    addFieldToResult(nextNode, resultNode, parts, index + 1, fullPath, objectMapper);
                } else if (nextNode.isArray()) {
                    ArrayNode arrayNode = resultNode.has(fullPath) ? (ArrayNode) resultNode.get(fullPath)
                            : objectMapper.createArrayNode();
                    for (JsonNode arrayElement : nextNode) {
                        ObjectNode tempNode = objectMapper.createObjectNode();
                        addFieldToResult(arrayElement, tempNode, parts, index + 1, fullPath, objectMapper);
                        arrayNode.add(tempNode.get(fullPath));
                    }
                    resultNode.set(fullPath, arrayNode);
                }
            }
        } else {
            String errorMessage = String.format(Constants.INVALID_FIELD, fullPath);
            throw new AppException(errorMessage);
        }
    }

    private JsonUtils() {
    }
}
