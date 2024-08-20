package com.hh.board.domain.post;


import com.hh.board.common.dto.SearchDto;
import com.hh.board.common.paging.PagingResponse;
import com.hh.board.common.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api-board")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Response> getPosts(SearchDto searchDto){

        PagingResponse<PostResponseDto> listWithPagination = postService.findAllPostBySearch(searchDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.success(listWithPagination));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Response> getPost(@PathVariable int postId){

        PostResponseDto postResponseDto = postService.findPostById(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Response.success(postResponseDto));
    }

    @PostMapping("/posts")
    public void savePost(){

    }

    @PatchMapping("/posts/{postId}")
    public void updatePost(@PathVariable int postId){

    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable int postId){

    }


}
