package com.hh.board.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hh.board.common.exception.CustomException;
import com.hh.board.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
public record ErrorResponse<E> (
        Instant timestamp,
        Integer status,
        String code,
        @JsonInclude(JsonInclude.Include.NON_NULL) E cause,
        String message
) {
    /**
     * custom exception 파싱
     * @param exception - custom exception
     * @return - response
     */
    public static ErrorResponse of(CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ErrorResponse.builder()
                .status(errorCode.defaultHttpStatus().value())
                .code(errorCode.name())
                .message(exception.getMessage())
                .cause(exception.getCause())
                .build();
    }

    // 컴팩트 생성자
    public ErrorResponse {
        if(code == null) {
            code = "API_ERROR";
        }
        if(status == null) {
            status = 500;
        }
        if(message.isBlank()) {
            message = "API 오류";
        }
        if(timestamp == null) {
            timestamp = Instant.now();
        }
    }
}
