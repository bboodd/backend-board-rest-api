package com.hh.board.domain.category;


import com.hh.board.common.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api-board")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<Response> getCategories(){

        List<CategoryDto> categoryList = categoryService.findAllCategory();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.success(categoryList));
    }
}
