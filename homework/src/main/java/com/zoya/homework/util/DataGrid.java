package com.zoya.homework.util;

import lombok.Data;

import java.util.List;


@Data
public class DataGrid {
    private Long total;
    private List<?> rows;
}
