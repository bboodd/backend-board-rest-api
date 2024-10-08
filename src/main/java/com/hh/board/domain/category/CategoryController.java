package com.hh.board.domain.category;


import com.hh.board.common.response.Response;
import com.hh.board.docs.CategoryControllerDocs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api-board")
public class CategoryController implements CategoryControllerDocs {

    private final CategoryService categoryService;

    // 카테고리 목록 조회
    @GetMapping("/categories")
    public ResponseEntity<Response> getCategories() {

        List<CategoryDto> categoryList = categoryService.findAllCategory();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(categoryList));
    }
}
