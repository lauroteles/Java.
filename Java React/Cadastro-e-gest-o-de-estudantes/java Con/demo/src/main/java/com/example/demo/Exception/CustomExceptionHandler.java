package com.example.demo.Exception;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CustomExceptionHandler {
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleExcepetion(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    return errors;    
    }
@ResponseStatus(HttpStatus.NOT_FOUND)
@ExceptionHandler(StudentNotFoundException.class)
public Map<String , String> userNotFound(StudentNotFoundException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return error;
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(StudentAlreadyExistsException.class)
public Map<String , String> usernotFound(StudentAlreadyExistsException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return error;
}

}
