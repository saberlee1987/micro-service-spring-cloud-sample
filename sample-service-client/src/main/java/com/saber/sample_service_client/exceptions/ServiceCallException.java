package com.saber.sample_service_client.exceptions;

public class ServiceCallException extends RuntimeException {
    private int statusCode;

    public ServiceCallException(int statusCode) {
        this.statusCode = statusCode;
    }

    public ServiceCallException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ServiceCallException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public ServiceCallException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public ServiceCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int statusCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.statusCode = statusCode;
    }

    public ServiceCallException() {
    }

    public ServiceCallException(String message) {
        super(message);
    }

    public ServiceCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceCallException(Throwable cause) {
        super(cause);
    }

    public ServiceCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
