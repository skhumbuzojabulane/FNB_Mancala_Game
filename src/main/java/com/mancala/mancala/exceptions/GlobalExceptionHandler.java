package com.mancala.mancala.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDTO handleValidationExceptions(
            MethodArgumentNotValidException ex) {
                ErrorResponseDTO errorResponse = new ErrorResponseDTO();
                ex.getBindingResult().getFieldErrors().forEach(error -> {
                    errorResponse.getViolations().add(new ValidationErrorDTO(error.getField(), error.getDefaultMessage()));
                });
                
                return errorResponse;
            }
            
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO handleGlobalExceptions(
            HttpServletRequest request, Exception ex) {
                ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.getViolations().add(new ValidationErrorDTO("error", "An internal server error occurred."));
        return errorResponse;
    }
}
