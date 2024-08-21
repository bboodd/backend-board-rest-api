package com.hh.board.domain.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 댓글 저장
     * @param commentVo - 댓글 정보
     * @return - 0 or 1
     */
    int saveComment(CommentVo commentVo);

    /**
     * 댓글 조회
     * @param commentId - id
     * @return - 댓글 정보
     */
    CommentVo findCommentById(int commentId);

    /**
     * 댓글 목록 조회
     * @param postId - 게시글 번호
     * @return - 댓글 list
     */
    List<CommentVo> findAllCommentByPostId(int postId);

    /**
     * 댓글 수정
     * @param commentVo - 댓글 수정 정보
     * @return - 0 or 1
     */
    int updateComment(CommentVo commentVo);

    /**
     * 댓글 삭제
     * @param commentId - id
     * @return - 0 or 1
     */
    int deleteCommentById(int commentId);
}
