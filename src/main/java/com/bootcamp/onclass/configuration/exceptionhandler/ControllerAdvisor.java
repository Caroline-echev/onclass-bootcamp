package com.bootcamp.onclass.configuration.exceptionhandler;



import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.EmptyFieldException;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.exception.NotUniqueNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionCodeResponse> handleEmptyFieldException(EmptyFieldException exception) {

        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(
                String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }

    @ExceptionHandler(NotUniqueNameException.class)
    public ResponseEntity<ExceptionCodeResponse> handleNotUniqueNameException() {

        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(Constants.TECHNOLOGY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionCodeResponse> handlerArgumentInvalidException(MethodArgumentNotValidException exception) {

        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(exception.getFieldErrors().getFirst().getDefaultMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionCodeResponse> handleNoDataFoundException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionCodeResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE,HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }




}
