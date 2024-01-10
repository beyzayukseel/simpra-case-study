package com.beyzanur.simpracasestudy.domain.exception;
import org.springframework.http.HttpStatusCode;

public class ExceptionHandler extends RuntimeException {

    private final ExceptionDescription exceptionDescription;
    private final HttpStatusCode httpStatusCode;

    public ExceptionHandler(ExceptionDescription exceptionDescription, HttpStatusCode httpStatusCode) {
        this.exceptionDescription = exceptionDescription;
        this.httpStatusCode = httpStatusCode;
    }
}
