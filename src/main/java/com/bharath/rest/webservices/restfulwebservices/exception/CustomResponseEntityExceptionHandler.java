package com.bharath.rest.webservices.restfulwebservices.exception;

import com.bharath.rest.webservices.restfulwebservices.User.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception{
    ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception{
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        String exceptions = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));

        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exceptions,request.getDescription(false));
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}

//The first method handles all exceptions and returns a response of 500 but for user not found i need to return a not found 404
// back so i put the UserNotFoundException.class inside the @ExceptionHandler to make the method handle only the UserNotFound
//exception and change the HttpStatus in the return to NotFound

//The third method overrides the method which handles the validations of the fields, so here we want to customise the response
//details so that it is convenient to the user to read and understand whats gone wrong, instead of passing the whole error log
//we want to only pass the messages defined in the validations. so all messages are available in the ex variable, we use its
//methods to extract them and pass them appropriately. change to  HttpStatus.BAD_REQUEST

//One more thing why we used override for the third method only not for the first two is because the first two methods parent
// is declared as final so we cant override it, but we can override the third method.

