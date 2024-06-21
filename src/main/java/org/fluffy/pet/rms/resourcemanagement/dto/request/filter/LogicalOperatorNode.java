package org.fluffy.pet.rms.resourcemanagement.dto.request.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.LogicalOperator;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize(as = LogicalOperatorNode.class)
public final class LogicalOperatorNode implements FilterNode {
    @JsonProperty(value = Constants.OPERATOR)
    @NotNull
    private LogicalOperator operator;

    private List<@Valid FilterNode> fields;
}
