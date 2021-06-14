package com.ua.main;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    String columnName();

    int columnOrder();

    String columnType();

}
