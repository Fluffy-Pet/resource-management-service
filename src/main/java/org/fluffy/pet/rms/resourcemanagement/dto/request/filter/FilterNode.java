package org.fluffy.pet.rms.resourcemanagement.dto.request.filter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.fluffy.pet.rms.resourcemanagement.deserializers.FilterNodeDeserializer;

@JsonDeserialize(using = FilterNodeDeserializer.class)
public sealed interface FilterNode permits ComparisonOperatorNode, LogicalOperatorNode {
}
