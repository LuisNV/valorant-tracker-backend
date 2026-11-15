package dev.luisnv.tracker.common;

/** The upstream stats API failed or rejected the request (rate limit, bad key, outage). */
public class UpstreamException extends RuntimeException {

    private final int upstreamStatus;

    public UpstreamException(int upstreamStatus, String message) {
        super(message);
        this.upstreamStatus = upstreamStatus;
    }

    public int getUpstreamStatus() {
        return upstreamStatus;
    }
}
