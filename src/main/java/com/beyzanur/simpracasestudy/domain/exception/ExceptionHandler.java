package com.beyzanur.simpracasestudy.domain.exception;
import com.beyzanur.simpracasestudy.model.GeneralException;
import org.springframework.http.HttpStatusCode;

public class ExceptionHandler extends RuntimeException {

    private final ExceptionDescription exceptionDescription;
    private final HttpStatusCode httpStatusCode;

    public ExceptionHandler(ExceptionDescription exceptionDescription, HttpStatusCode httpStatusCode) {
        this.exceptionDescription = exceptionDescription;
        this.httpStatusCode = httpStatusCode;
    }

    public GeneralException getExceptionDetails() {
        return new GeneralException(
                this.exceptionDescription.toString(),
                this.httpStatusCode.toString()
        );
    }
}
