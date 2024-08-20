package com.hh.board.domain.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int saveComment(CommentVo commentVo);

    CommentVo findCommentById(int commentId);

    List<CommentVo> findAllCommentByPostId(int postId);

    int updateComment(CommentVo commentVo);

    int deleteCommentById(int commentId);
}
