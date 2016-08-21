package com.author.base.exception;

/**
 * Created by Administrator on 2016/8/8.
 */

public class InitializationException extends Exception {
    private static final long serialVersionUID = 1L;

    public InitializationException() {
    }

    public InitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializationException(String message) {
        super(message);
    }

    public InitializationException(Throwable cause) {
        super(cause);
    }
}
