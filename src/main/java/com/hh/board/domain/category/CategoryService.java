package com.hh.board.domain.category;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    /**
     * 카테고리 목록 조회
     * @return 카테고리 리스트
     */

    public List<CategoryDto> findAllCategory(){
        List<CategoryVo> categoryList = categoryMapper.findAllCategory();

        List<CategoryDto> result = categoryList.stream()
                .map(CategoryDto::toDto).collect(toList());
        return result;
    }
}
