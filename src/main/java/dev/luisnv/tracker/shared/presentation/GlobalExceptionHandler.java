package dev.luisnv.tracker.shared.presentation;

import dev.luisnv.tracker.shared.domain.exception.NotFoundException;
import dev.luisnv.tracker.shared.domain.exception.StatsProviderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ProblemDetail handleNotFound(NotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(StatsProviderException.class)
    ProblemDetail handleProvider(StatsProviderException ex) {
        HttpStatus status = ex.isRateLimited() ? HttpStatus.TOO_MANY_REQUESTS : HttpStatus.BAD_GATEWAY;
        return ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    }
}
