package com.ua.main;

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
        String rowValues = getFieldAnnotatedColumn()
                .stream()
                .map(this::getFieldValue)
                .collect(Collectors.joining(","));
        return rowValues.split(",");
    }


    public List<Field> getFieldAnnotatedColumn() {
        return Arrays.stream(getClass().getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());
    }

    private String getFieldValue(Field field) {
        try {
            return field != null ? field.get(this).toString() : null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    public String[] getColumn() {
        String columnValues = getFieldAnnotatedColumn()
                .stream()
                .map(f -> f.getAnnotation(Column.class).columnName())
                .collect(Collectors.joining(","));
        return columnValues.split(",");
    }

}