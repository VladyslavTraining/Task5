package com.delphi.parsers;

import com.delphi.data.AbstractColumnData;

import java.util.List;

public interface Parser<T extends AbstractColumnData> {
    List<T> parse(byte[] arr);
}
