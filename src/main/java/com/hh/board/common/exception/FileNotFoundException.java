package com.hh.board.common.exception;

public class FileNotFoundException extends RuntimeException {

    private String message;

    public FileNotFoundException() {
        this.message = "파일을 찾을 수 없습니다.";
    }

    public FileNotFoundException(String message) {
        this.message = message;
    }
}
