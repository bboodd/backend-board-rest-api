package com.hh.board.domain.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class CategoryDto {

    private int categoryId;

    private String categoryName;

    /**
     * vo to dto
     * 카테고리 내용을 화면에 뿌려주기위해
     * @param categoryVo
     * @return categoryDto
     */

    public static CategoryDto toDto(CategoryVo categoryVo){
        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(categoryVo.getCategoryId())
                .categoryName(categoryVo.getCategoryName())
                .build();
        return categoryDto;
    }
}
