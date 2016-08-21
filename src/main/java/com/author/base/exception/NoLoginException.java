package com.author.base.exception;

/**
 * Created by Administrator on 2016/8/8.
 */
import javax.servlet.ServletException;

public class NoLoginException extends ServletException {
    public NoLoginException() {
    }

    public NoLoginException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public NoLoginException(String message) {
        super(message);
    }

    public NoLoginException(Throwable rootCause) {
        super(rootCause);
    }
}
