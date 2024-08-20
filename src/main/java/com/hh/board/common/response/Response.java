package com.hh.board.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class Response {
    private boolean success;
    private int code;
    private Result result;

    /**
     * 데이터가 없이 성공 반환 해주는 경우
     * @return
     */
    public static Response success() {
        return new Response(true, 0, null);
    }

    /**
     * 데이터를 포함해서 성공 반환 해주는 경우
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Response success(T data) {
        return new Response(true, 0, new Success<>(data));
    }

    public static Response error(int code, String message) {
        return new Response(false, code, new Error(message));
    }
}
