package com.hh.board.domain.post;


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
    public void getPosts(){

    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable int postId){

        PostResponseDto postResponseDto = postService.findPostById(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postResponseDto);
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
