package com.hh.board.domain.comment;


import com.hh.board.common.response.Response;
import com.hh.board.docs.CommentControllerDocs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hh.board.domain.comment.CommentVo.toVo;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api-board")
public class CommentController implements CommentControllerDocs {

    private final CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Response> getComments(@PathVariable int postId){

        List<CommentResponseDto> commentList = commentService.findAllCommentByPostId(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(commentList));

    }

    // 댓글 조회
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Response> getComment(@PathVariable int postId, @PathVariable int commentId) {

        CommentResponseDto commentResponseDto = commentService.findCommentById(commentId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(commentResponseDto));
    }

    // 댓글 등록
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Response> saveComment(@PathVariable int postId
                          , @RequestBody @Valid CommentRequestDto commentRequestDto) {

        int commentId = commentService.saveComment(toVo(commentRequestDto));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(commentId + "번 댓글 등록 완료"));
    }

    // 댓글 수정
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Response> updateComment(@PathVariable int postId, @PathVariable int commentId
                            , @RequestBody @Valid CommentRequestDto commentRequestDto) {

        commentService.updateComment(toVo(commentRequestDto));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(commentId + "번 댓글 수정 완료"));
    }

    // 댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Response> deleteComment(@PathVariable int postId, @PathVariable int commentId) {

        commentService.deleteComment(commentId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new Response(commentId + "번 댓글 삭제 완료"));
    }
}
