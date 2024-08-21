package com.hh.board.common.exception;

public class PostNotFoundException extends RuntimeException {

    private String message;

    public PostNotFoundException() {
        this.message = "게시글을 찾을 수 없습니다.";
    }

    public PostNotFoundException(String message) {
        this.message = message;
    }
}
