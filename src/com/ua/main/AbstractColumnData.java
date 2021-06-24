package com.ua.main;

import java.lang.reflect.Field;
import java.util.Arrays;


public abstract class AbstractColumnData {


    public String getValue(String key) {
        Field field = Arrays.stream(getClass().getDeclaredFields())
                .peek(x -> x.setAccessible(true))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .filter(x -> x.getName().equalsIgnoreCase(key))
                .findAny().orElse(null);
        try {
            if (field != null) return String.valueOf(field.get(this));
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
        return null;
    }

    public String[] getRow() {
        String[] row = new String[1];
        StringBuilder sb = new StringBuilder();
        Arrays.stream(getClass().getDeclaredFields())
                .peek(x -> x.setAccessible(true))
                .filter(x -> x.isAnnotationPresent(Column.class))
                .forEach(x -> {
                    try {
                        sb.append(x.get(this)).append(",");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        row[0] = sb.substring(0, sb.length() - 1);
        return row;
    }


    public String[] getColumn() {
        String[] column = new String[1];
        StringBuilder sb = new StringBuilder();
        Arrays.stream(getClass().getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .forEach(f -> sb.append(f.getAnnotation(Column.class).columnName()).append(","));
        column[0] = sb.substring(0, sb.length() - 1);
        return column;
    }

}