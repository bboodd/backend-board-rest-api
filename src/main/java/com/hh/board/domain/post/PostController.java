package com.hh.board.domain.post;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/api-board")
public class PostController {

    @GetMapping("/posts")
    public void getPosts(){

    }

    @GetMapping("/post/{postId}")
    public void getPost(@PathVariable int postId){

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
