package com.co.indra.coinmarketcap.users.exceptions;

import com.co.indra.coinmarketcap.users.responses.ErrorResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody //que la respuesta va a ser personalizada.
    @ExceptionHandler(BusinessExceptions.class)
    public ErrorResponse handleBusinessException(BusinessExceptions exception) {
        return new ErrorResponse(exception.getCode(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse("NOT_FOUND", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleNotFoundException(MethodArgumentNotValidException exception) {
        return new ErrorResponse("BAD_REQUEST", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception) {
   	 
        return new ErrorResponse("500", exception.getMessage());
    }
}