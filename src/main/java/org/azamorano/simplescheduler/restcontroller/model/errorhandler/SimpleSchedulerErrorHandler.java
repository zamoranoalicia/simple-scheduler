package org.azamorano.simplescheduler.restcontroller.model.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@Slf4j
@Component
@RestControllerAdvice
public class SimpleSchedulerErrorHandler extends ResponseEntityExceptionHandler {
    private static final String messageError = "Scheduler Service Error";
    private static final String COMMA_SEPARATOR = ", ";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NonNull Exception exception,
                                                             Object body,
                                                             @NonNull HttpHeaders headers,
                                                             @NonNull HttpStatus status,
                                                             @NonNull WebRequest request) {
        headers.setContentType(APPLICATION_JSON);
        log.error(messageError, exception);

        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> format("%s %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(joining(COMMA_SEPARATOR));
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(errorMessage)
                .statusCode(BAD_REQUEST.value())
                .build();
        return  handleExceptionInternal(exception, errorResponse, headers, status, request);
    }
}
