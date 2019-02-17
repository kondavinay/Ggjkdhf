package com.repo.doc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.repo.doc.error.ExceptionResponse;
import com.repo.doc.error.ProcessorException;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ProcessorException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ProcessorException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("400");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
}
