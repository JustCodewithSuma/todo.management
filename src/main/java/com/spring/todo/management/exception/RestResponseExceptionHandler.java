package com.spring.todo.management.exception;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method for handling the HttpClientErrorException.Forbidden
     */
    @ExceptionHandler(value = {HttpClientErrorException.Forbidden.class})
    protected ResponseEntity<Object> handleForbiddenException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    /**
     * Method for handling the HttpClientErrorException.NotFound
     */
    @ExceptionHandler(value = {HttpClientErrorException.NotFound.class})
    protected ResponseEntity<Object> handleNotFoundException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    /**
     * Method for handling the HttpClientErrorException.TooManyRequests
     */
    @ExceptionHandler(value = {HttpClientErrorException.TooManyRequests.class})
    protected ResponseEntity<Object> handleTooManyRequestsException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.TOO_MANY_REQUESTS, request);
    }

    /**
     * Method for handling the TimeoutException
     */
    @ExceptionHandler(value
            = {TimeoutException.class})
    protected ResponseEntity<Object> handleTimeOutException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.REQUEST_TIMEOUT, request);
    }

    /**
     * Method for handling the HttpClientErrorException.UnprocessableEntity
     */
    @ExceptionHandler(value
            = {HttpClientErrorException.UnprocessableEntity.class})
    protected ResponseEntity<Object> handleBadFormatRequest(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    /**
     * Method for handling the IllegalArgumentException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Method for handling the HttpClientErrorException.BadRequest
     */
    @ExceptionHandler(value
            = {HttpClientErrorException.BadRequest.class})
    protected ResponseEntity<Object> handleBadRequestException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Method for handling the HttpServerErrorException.InternalServerError
     */
    @ExceptionHandler(value
            = {HttpServerErrorException.InternalServerError.class})
    protected ResponseEntity<Object> handleInternalServerException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    /**
     * Method for handling the DataAccessResourceFailureException,BusinessInputValidationException
     */
    @ExceptionHandler(value = {DataAccessResourceFailureException.class,BusinessInputValidationException.class,NoSuchElementException.class})
    protected ResponseEntity<Object> handleDataAccessResourceFailureException(
            RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
