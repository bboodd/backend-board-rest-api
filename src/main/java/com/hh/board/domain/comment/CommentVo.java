package com.hh.board.domain.comment;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Value
//객체 외부 생성 제한
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class CommentVo {

    private int commentId;

    private int postId;

    private String content;

    private LocalDateTime createDate;

    /**
     * dto를 vo로
     * db에 넣기위함
     * @param commentRequestDto
     * @return commentVo
     */

    public static CommentVo toVo(CommentRequestDto commentRequestDto){
        CommentVo commentVo = CommentVo.builder()
                .commentId(commentRequestDto.getCommentId())
                .postId(commentRequestDto.getPostId())
                .content(commentRequestDto.getContent())
                .build();
        return commentVo;
    }
}
