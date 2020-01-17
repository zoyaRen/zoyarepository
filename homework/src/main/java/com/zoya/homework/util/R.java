package com.zoya.homework.util;

import java.util.HashMap;
import java.util.List;

public class R extends HashMap {

    private Integer code;
    private List<?> resultList;

    public R(Integer code, List<?> resultList) {
        this.put("code",code);
        this.put("resultList",resultList);
    }
}
