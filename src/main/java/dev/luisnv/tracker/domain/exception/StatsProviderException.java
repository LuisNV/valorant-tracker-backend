package dev.luisnv.tracker.domain.exception;

/** The external stats provider failed or rejected the request (rate limit, bad key, outage). */
public class StatsProviderException extends RuntimeException {

    private final boolean rateLimited;

    public StatsProviderException(String message, boolean rateLimited) {
        super(message);
        this.rateLimited = rateLimited;
    }

    public boolean isRateLimited() {
        return rateLimited;
    }
}
