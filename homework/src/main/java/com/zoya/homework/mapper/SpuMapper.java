package com.zoya.homework.mapper;

import com.zoya.homework.domain.Spu;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuMapper extends Mapper<Spu> {

    @Select("select spu.*,tbb.name bname ,CONCAT(tbc1.name,'/',tbc2.name,'/',tbc3.name) cname from spu \n" +
            "LEFT JOIN tb_brand tbb on tbb.id = spu.brand_id\n" +
            "LEFT JOIN tb_category tbc1 on spu.cid1 = tbc1.id\n" +
            "LEFT JOIN tb_category tbc2 on spu.cid2 = tbc2.id\n" +
            "LEFT JOIN tb_category tbc3 on spu.cid3 = tbc3.id")
    List<Spu> selectAllInfos();

    @Select("select spu.*,tbb.name bname ,CONCAT(tbc1.name,'/',tbc2.name,'/',tbc3.name) cname from spu \n" +
            "LEFT JOIN tb_brand tbb on tbb.id = spu.brand_id\n" +
            "LEFT JOIN tb_category tbc1 on spu.cid1 = tbc1.id\n" +
            "LEFT JOIN tb_category tbc2 on spu.cid2 = tbc2.id\n" +
            "LEFT JOIN tb_category tbc3 on spu.cid3 = tbc3.id\n" +
            "where spu.title like concat('%',#{title},'%')")
    List<Spu> selectAllInfosByTitle(String title);
}
