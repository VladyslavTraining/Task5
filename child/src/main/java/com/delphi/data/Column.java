package com.delphi.data;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    String columnName();

    int columnOrder();

    String columnType();
}
