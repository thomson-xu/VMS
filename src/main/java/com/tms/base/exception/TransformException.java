package com.tms.base.exception;

/**
 * Created by Administrator on 2016/8/8.
 */

public class TransformException extends Exception {
    public TransformException() {
    }

    public TransformException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransformException(String message) {
        super(message);
    }

    public TransformException(Throwable cause) {
        super(cause);
    }
}
