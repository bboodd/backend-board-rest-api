package com.hh.board.domain.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    /**
     * 댓글 등록 후 id 반환
     * @param commentVo
     * @return commentId
     */

    @Transactional
    public int saveComment(CommentVo commentVo){
        commentMapper.saveComment(commentVo);
        int resultId = commentVo.getCommentId();
        return resultId;
    }

    /**
     * 댓글 id로 댓글 찾기
     * @param commentId
     * @return responseDto
     */

    public CommentResponseDto findCommentById(int commentId){
        CommentVo commentVo = commentMapper.findCommentById(commentId);
        return CommentResponseDto.toDto(commentVo);
    }

    /**
     * 댓글 업데이트
     * @param commentVo
     * @return commentId
     */

    public int updateComment(CommentVo commentVo) {
        commentMapper.updateComment(commentVo);
        return commentVo.getCommentId();
    }

    /**
     * 댓글 삭제
     * @param commentId
     * @return
     */

    @Transactional
    public int deleteComment(int commentId){
        commentMapper.deleteCommentById(commentId);
        return commentId;
    }

    /**
     * 댓글 목록 조회
     * @param postId
     * @return
     */

    public List<CommentResponseDto> findAllCommentByPostId(int postId){
        List<CommentVo> commentList = commentMapper.findAllCommentByPostId(postId);

        List<CommentResponseDto> result = commentList.stream()
                .map(CommentResponseDto::toDto).collect(toList());
        return result;
    }
}
