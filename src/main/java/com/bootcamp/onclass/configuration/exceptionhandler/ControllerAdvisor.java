package com.bootcamp.onclass.configuration.exceptionhandler;



import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.DuplicateItemsListException;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.exception.ValidateDateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {


    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<ExceptionCodeResponse> handleElementAlreadyExistsException() {

        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(Constants.ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionCodeResponse> handlerArgumentInvalidException(MethodArgumentNotValidException exception) {

        FieldError firstFieldError = exception.getFieldErrors().get(0);
        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(firstFieldError.getDefaultMessage(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionCodeResponse> handleNoDataFoundException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionCodeResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE,HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }
    @ExceptionHandler(DuplicateItemsListException.class)
    public ResponseEntity<ExceptionCodeResponse> handleDuplicateItemsListException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionCodeResponse(
                Constants.DUPLICATE_ITEMS_LIST_EXCEPTION_MESSAGE,HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }


    @ExceptionHandler(ValidateDateException.class)
    public ResponseEntity<ExceptionCodeResponse> handleValidateDateException() {

        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(Constants.VALIDATE_DATE_RANGE_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ExceptionCodeResponse> handleDateTimeParseException() {

        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(Constants.INVALID_DATE_FORMAT_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }





}
