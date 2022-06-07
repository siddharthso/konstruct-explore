package com.spacifii.konstruct.explore.annotation;


import com.spacifii.konstruct.explore.annotation.entity.AttributeType;

public @interface Attribute {

    AttributeType type() default AttributeType.SINGLE;

    String keyword() default "";

    String resolvedKeyword() default "";

    boolean enableFindBy() default false;

    Class<?> dataType() default Object.class;

}
