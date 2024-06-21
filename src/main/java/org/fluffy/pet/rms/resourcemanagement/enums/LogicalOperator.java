package org.fluffy.pet.rms.resourcemanagement.enums;

import java.util.HashSet;
import java.util.Set;

public enum LogicalOperator {
    AND,
    OR,
    ;

    private static final Set<String> operatorValues;

    static {
        LogicalOperator[] values = LogicalOperator.values();
        operatorValues = HashSet.newHashSet(values.length);
        for (LogicalOperator logicalOperator : values) {
            operatorValues.add(logicalOperator.name());
        }
    }

    public static boolean exists(String value) {
        return operatorValues.contains(value);
    }
}
