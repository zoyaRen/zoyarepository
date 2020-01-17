package com.zoya.homework.web;

import com.zoya.homework.domain.Spu;
import com.zoya.homework.service.SpuService;
import com.zoya.homework.util.DataGrid;
import com.zoya.homework.util.QueryDTO;
import com.zoya.homework.util.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class SpuController {

    @Resource
    private SpuService spuService;


    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public R selectOneSpuById(int id){
        return spuService.selectOneSpuById(id);
    };

    @RequestMapping(value = "/title",method = RequestMethod.GET)
    public DataGrid selectSpusByPageAndTitle(QueryDTO dto){
        if(dto.getSearch()==null || dto.getSearch()=="" ){
            return spuService.selectSpusByPage(dto.getLimit(),dto.getOffset());
        }
        return spuService.selectSpusByPageAndTitle(dto.getLimit(),dto.getOffset(),dto.getSearch());
    };

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public DataGrid selectSpusByPage(@RequestParam(value = "limit",defaultValue = "5") int limit,@RequestParam(value = "offset",defaultValue = "0") int offset){
        System.out.println("2="+spuService.selectSpusByPage(limit,offset));
        return spuService.selectSpusByPage(limit,offset);
    };

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public R selectSpusInfos(){
        return spuService.selectSpusInfos();
    };


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public R addOneSpu(@RequestBody Spu spu){
        Date now =new Date();
        spu.setCreateTime(now);
        spu.setLastUpdateTime(now);
        return spuService.addOneSpu(spu);
    };

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public R updateOneSpuById(@RequestBody Spu spu){
        Date now =new Date();
        spu.setLastUpdateTime(now);
        return spuService.updateOneSpuById(spu);
    };

    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public R deleteOneSpuByIds(@RequestBody List<Integer> ids){
        return spuService.deleteOneSpuByIds(ids);
    };

}
