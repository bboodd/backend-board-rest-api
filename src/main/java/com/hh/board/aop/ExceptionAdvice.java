package com.hh.board.aop;

import com.hh.board.common.exception.PostNotFoundException;
import com.hh.board.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * id로 게시글을 찾을 수 없을 때
     * @return 404
     */
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> postNotFoundException(){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("게시글을 찾을 수 없습니다."));
    }

    /**
     * 400에러
     * 요청 객체의 validation을 수행할 때, MethodArgumentNotValidException이 발생
     * 각 검증 어노테이션 별로 지정해놨던 메시지를 응답
     * @param e
     * @return 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(message));
    }
}
