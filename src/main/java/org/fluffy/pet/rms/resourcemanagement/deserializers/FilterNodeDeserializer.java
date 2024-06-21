package org.fluffy.pet.rms.resourcemanagement.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.ComparisonOperatorNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.LogicalOperatorNode;
import org.fluffy.pet.rms.resourcemanagement.enums.LogicalOperator;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FilterNodeDeserializer extends StdDeserializer<FilterNode> {

    public FilterNodeDeserializer() {
        super(FilterNode.class);
    }

    @Override
    public FilterNode deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode jsonNode = parser.getCodec().readTree(parser);
        return mapNode(parser, jsonNode);
    }

    private FilterNode mapNode(JsonParser parser, JsonNode jsonNode) throws IOException {
        JsonNode operatorNode = jsonNode.get(Constants.OPERATOR);
        if (operatorNode == null || operatorNode.isNull()) {
            return new ComparisonOperatorNode();
        }
        String operatorNodeText = operatorNode.asText();

        if (LogicalOperator.exists(operatorNodeText)) {
            return parser.getCodec().treeToValue(jsonNode, LogicalOperatorNode.class);
        } else {
            return parser.getCodec().treeToValue(jsonNode, ComparisonOperatorNode.class);
        }
    }
}
