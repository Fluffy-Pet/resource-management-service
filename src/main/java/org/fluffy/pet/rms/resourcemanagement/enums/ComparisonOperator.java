package org.fluffy.pet.rms.resourcemanagement.enums;

import java.util.HashSet;
import java.util.Set;

public enum ComparisonOperator {
    GTE,
    LTE,
    EQUALS,
    NOT_EQUALS,
    GT,
    LT,
    IN,
    NIN,
    REGEX;

    private static final Set<String> operatorValues;

    static {
        ComparisonOperator[] values = ComparisonOperator.values();
        operatorValues = HashSet.newHashSet(values.length);
        for (ComparisonOperator comparisonOperator : values) {
            operatorValues.add(comparisonOperator.name());
        }
    }

    public static boolean exists(String value) {
        return operatorValues.contains(value);
    }
}
