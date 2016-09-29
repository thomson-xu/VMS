package com.tms.base.exception;

/**
 * Created by Administrator on 2016/8/8.
 */

public class DateFormatException extends Exception {
    private static final long serialVersionUID = 1901344100777586483L;

    public DateFormatException() {
    }

    public DateFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateFormatException(String message) {
        super(message);
    }

    public DateFormatException(Throwable cause) {
        super(cause);
    }
}