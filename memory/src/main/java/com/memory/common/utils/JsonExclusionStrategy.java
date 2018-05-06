package com.memory.common.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.lang.annotation.Annotation;

public class JsonExclusionStrategy implements ExclusionStrategy {
    private Class<? extends Annotation> skipAnnotation ;

    public JsonExclusionStrategy(Class<? extends Annotation> skipAnnotation) {
        this.skipAnnotation = skipAnnotation;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getAnnotation(skipAnnotation) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
