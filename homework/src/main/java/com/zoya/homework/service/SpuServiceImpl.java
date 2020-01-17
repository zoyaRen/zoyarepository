package com.zoya.homework.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoya.homework.domain.Spu;
import com.zoya.homework.mapper.SpuMapper;
import com.zoya.homework.util.DataGrid;
import com.zoya.homework.util.R;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Resource
    private SpuMapper mapper;

    @Override
    public R selectSpusByTitle(String title) {
        List<Spu> spuList = mapper.selectAllInfosByTitle(title);
        return new R(spuList.size(),spuList);
    }

    @Override
    public R selectOneSpuById(Integer id) {
        Spu spu = mapper.selectByPrimaryKey(id);
        List<Spu> spuList =new ArrayList<>();
        spuList.add(spu);
        return new R(spuList.size(),spuList);
    }

    @Override
    public R addOneSpu(Spu spu) {
        Integer code =mapper.insert(spu);
        if(code!=0){
            List<Spu> spuList = mapper.selectAllInfos();
            return new R(code,spuList);
        }
        return new R(0,null);
    }

    @Override
    public R updateOneSpuById(Spu spu) {
        Integer code =mapper.updateByPrimaryKeySelective(spu);
        if(code!=0){
            List<Spu> spuList = mapper.selectAllInfos();
            return new R(code,spuList);
        }
        return new R(0,null);
    }

    @Override
    public R deleteOneSpuByIds(List<Integer> ids) {
        Example example = new Example(Spu.class);
        example.createCriteria().andIn("id", ids);
        Integer code =mapper.deleteByExample(example);
        if(code!=0){
            List<Spu> spuList = mapper.selectAllInfos();
            return new R(code,spuList);
        }
        return new R(0,null);
    }

    @Override
    public DataGrid selectSpusByPageAndTitle(Integer limit, Integer offset, @RequestParam("title") String title) {
        PageHelper.offsetPage(offset, limit);
        List<Spu> spuList = mapper.selectAllInfosByTitle(title);

        PageInfo<Spu> info  = new PageInfo<>(spuList);

        DataGrid grid = new DataGrid();
        grid.setRows(info.getList());
        grid.setTotal(info.getTotal());
        return grid;
    }

    @Override
    public DataGrid selectSpusByPage(Integer limit, Integer offset) {
        PageHelper.offsetPage(offset, limit);
        List<Spu> spuList = mapper.selectAllInfos();
        System.out.println("1="+spuList);
        PageInfo<Spu> info  = new PageInfo<>(spuList);
        DataGrid grid = new DataGrid();
        grid.setRows(info.getList());
        grid.setTotal(info.getTotal());
        return grid;
    }

    @Override
    public R selectSpusInfos() {
        List<Spu> spuList = mapper.selectAllInfos();
        return new R(spuList.size(),spuList);
    }
}
