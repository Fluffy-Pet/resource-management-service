package org.fluffy.pet.rms.resourcemanagement.dto.request.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.ComparisonOperator;
import org.fluffy.pet.rms.resourcemanagement.util.Constants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize(as = ComparisonOperatorNode.class)
public final class ComparisonOperatorNode implements FilterNode {
    @JsonProperty(value = Constants.OPERATOR)
    @NotNull
    private ComparisonOperator operator;

    @NotBlank
    private String field;

    @NotNull
    private JsonNode value;
}
