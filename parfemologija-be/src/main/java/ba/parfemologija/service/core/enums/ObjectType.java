package ba.parfemologija.service.core.enums;

import java.util.EnumMap;
import java.util.Map;

public enum ObjectType {
    /** ENTITY */
    SAMPLE("SAMPLE");

    private final String value;

    private static final Map<ObjectType, String> classNameMap = new EnumMap<>(ObjectType.class);

    static {
        classNameMap.put(ObjectType.SAMPLE, "path.to.the.entity.Entity");

    }

    ObjectType(final String value) {
        this.value = value;
    }

    public String getClassName() {
        return classNameMap.get(this);
    }

    public String getValue() {
        return value;
    }
    }
