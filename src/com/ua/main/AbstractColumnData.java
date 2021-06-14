package com.ua.main;

import java.lang.reflect.Field;


public abstract class AbstractColumnData {


    public String getValue(String key) {
        Field[] fields = this.getClass().getDeclaredFields();
        String value = null;
        for (Field f : fields) {
            f.setAccessible(true);
            Column c = f.getAnnotation(Column.class);
            if (c != null) {
                if (f.getName().equals(key)) {
                    try {
                        value = (String) f.get(this);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return value;
    }

    public String[] getRow() {
        String[] row = new String[1];
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Field f : fields) {
            f.setAccessible(true);
            Column column = f.getAnnotation(Column.class);
            try {
                if (column != null) {
                    if (counter == fields.length - 1) {
                        sb.append(f.get(this));
                    } else {
                        sb.append(f.get(this)).append(",");
                    }
                    counter++;
                } else
                    sb.append("null");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        row[0] = sb.toString();
        return row;
    }


    public String[] getColumn() {
        String[] column = new String[1];
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            Column c = field.getAnnotation(Column.class);
            if (c != null) {
                if (counter == fields.length - 1) {
                    sb.append(field.getName());
                } else {
                    sb.append(field.getName()).append(",");
                }
                counter++;
            } else
                sb.append("null");
        }
        column[0] = sb.toString();
        return column;
    }

}