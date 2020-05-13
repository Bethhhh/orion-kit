package com.orion.exception;

/**
 * 执行失败异常
 *
 * @author ljh15
 * @version 1.0.0
 * @date 2020/4/16 17:38
 */
public class ExecuteException extends Exception {

    public ExecuteException() {
    }

    public ExecuteException(String message) {
        super(message);
    }

    public ExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecuteException(Throwable cause) {
        super(cause);
    }

}
