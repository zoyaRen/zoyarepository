package com.zoya.homework.util;

import lombok.Data;

@Data
public class QueryDTO {
    private String order;
    private Integer limit;
    private Integer offset;
    private String sort;
    private String search;

}
