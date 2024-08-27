package com.hh.board.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
// TODO: 에러랑 성공이랑 따로 설계
public class Response {
    private boolean success;
    private int code;
    private Result result;

    /**
     * 데이터가 없이 성공 반환 해주는 경우
     * @return - Response
     */
    public static Response success() {
        return new Response(true, 0, null);
    }

    /**
     * 데이터를 포함해서 성공 반환 해주는 경우
     * @param data - data
     * @return - Response
     * @param <T> - general
     */
    public static <T> Response success(T data) {
        return new Response(true, 0, new Success<>(data));
    }

    /**
     * 에러 발생시 메세지 반환
     * @param code - 에러코드
     * @param message - 메세지
     * @return - Response
     */
    public static Response error(int code, String message) {
        return new Response(false, code, new Error(message));
    }
}
