package com.hh.board.domain.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class CommentRequestDto {

    private int commentId;

    private int postId;

    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String content;

}
