package com.ua.main;

import java.lang.reflect.Field;


public abstract class AbstractColumnData {


    public String getValue(String key) {
        return null;
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
        return null;
    }
}