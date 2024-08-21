package com.hh.board.domain.category;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 카테고리 목록 조회
     * @return 카테고리 list
     */
    List<CategoryVo> findAllCategory();
}
