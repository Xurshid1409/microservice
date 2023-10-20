package uz.jurayev.account.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.jurayev.account.constants.AccountConstant;
import uz.jurayev.account.dto.ErrorResponseDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        allErrors.forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            validationErrors.put(field, message);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto globalExceptionHandler(Exception ex, WebRequest request) {
        return new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now());
    }

    @ExceptionHandler(value = {CustomerExistException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDto customerExistHandler(CustomerExistException ex, WebRequest request) {
        return new ErrorResponseDto(AccountConstant.STATUS_CODE_400.getMessage(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now());
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponseDto resourceNotFoundHandler(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now());
    }
}
