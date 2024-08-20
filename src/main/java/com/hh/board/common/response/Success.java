package com.hh.board.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL) // null 값을 가지는 필드는 JSON 응답에 포함x
@Getter
@AllArgsConstructor
public class Success<T> implements Result{
    private T data;
}
