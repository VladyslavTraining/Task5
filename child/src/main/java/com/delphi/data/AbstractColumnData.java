package com.delphi.data;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbstractColumnData {

    public String getValue(String key) {
        Field field = getFieldAnnotatedColumn()
                .stream()
                .filter(f -> f.getName().equalsIgnoreCase(key))
                .findAny()
                .orElse(null);
        return getFieldValue(field);
    }

    public String[] getRow() {
        return getFieldAnnotatedColumn()
                .stream()
                .map(this::getFieldValue)
                .toArray(String[]::new);
    }


    public List<Field> getFieldAnnotatedColumn() {
        return Arrays.stream(getClass().getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());
    }

    private String getFieldValue(Field field) {
        try {
            return field.get(this).toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    public String[] getColumn() {
        return getFieldAnnotatedColumn()
                .stream()
                .map(f -> f.getAnnotation(Column.class).columnName())
                .toArray(String[]::new);
    }

}