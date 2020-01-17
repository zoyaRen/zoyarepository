package com.zoya.homework.web;

import com.zoya.homework.domain.Spu;
import com.zoya.homework.service.SpuService;
import com.zoya.homework.util.DataGrid;
import com.zoya.homework.util.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
public class IndexController {


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "sup";
    };


    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String index2(){
        return "user";
    };


}
