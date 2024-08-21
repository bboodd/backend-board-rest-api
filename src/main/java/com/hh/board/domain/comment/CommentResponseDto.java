package com.hh.board.domain.comment;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {

    private int commentId;
    private int postId;
    private String content;
    private LocalDateTime createDate;

    /**
     * vo객체를 dto객체로
     * 다운로드용 화면에 뿌려주기 위함
     * @param commentVo - vo
     * @return responseDto - dto
     */

    public static CommentResponseDto toDto(CommentVo commentVo) {
        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .commentId(commentVo.getCommentId())
                .postId(commentVo.getPostId())
                .content(commentVo.getContent())
                .createDate(commentVo.getCreateDate())
                .build();
        return commentResponseDto;
    }
}
