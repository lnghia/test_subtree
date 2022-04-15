package com.example.demo.Exception;

import com.example.demo.dto.Response.ResponseBodyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UsernamePasswordInvalidException.class)
    public ResponseEntity<Object> handleUsernamePasswordInvalidException(UsernamePasswordInvalidException exception, WebRequest webRequest){
        ResponseBodyDTO response = new ResponseBodyDTO();

        response.getErrors().add("Invalid username or password");

        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }
}
