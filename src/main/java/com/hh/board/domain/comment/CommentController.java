package com.hh.board.domain.comment;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api-board")
public class CommentController {

    private final CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public void getComments(@PathVariable int postId){

    }

    // 댓글 조회
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public void getComment(@PathVariable int postId, @PathVariable int commentId) {

    }

    // 댓글 저장
    @PostMapping("/posts/{postId}/comments")
    public void saveComment(@PathVariable int postId) {

    }

    // 댓글 수정
    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public void updateComment(@PathVariable int postId, @PathVariable int commentId) {

    }

    // 댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable int postId, @PathVariable int commentId) {

    }
}
