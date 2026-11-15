package dev.luisnv.tracker.presentation;

import dev.luisnv.tracker.domain.exception.PlayerNotFoundException;
import dev.luisnv.tracker.domain.exception.StatsProviderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    ProblemDetail handleNotFound(PlayerNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(StatsProviderException.class)
    ProblemDetail handleProvider(StatsProviderException ex) {
        HttpStatus status = ex.isRateLimited() ? HttpStatus.TOO_MANY_REQUESTS : HttpStatus.BAD_GATEWAY;
        return ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    }
}
