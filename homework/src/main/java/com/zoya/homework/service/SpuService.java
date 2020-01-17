package com.zoya.homework.service;

import com.zoya.homework.domain.Spu;
import com.zoya.homework.util.DataGrid;
import com.zoya.homework.util.R;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpuService {

    R selectSpusByTitle(@Param("title") String title);

    R selectOneSpuById(@Param("id") Integer id);

    R addOneSpu(Spu spu);

    R updateOneSpuById(Spu spu);

    R deleteOneSpuByIds(@Param("id") List<Integer> ids);

    DataGrid selectSpusByPageAndTitle(@Param("page") Integer page, @Param("size") Integer size, @Param("title") String title);

    DataGrid selectSpusByPage(@Param("page") Integer page, @Param("size") Integer size);

    R selectSpusInfos();
}
