package com.quangduong.SE114backend.exception;

public class NoPermissionException extends RuntimeException {

    public NoPermissionException(String message) {
        super(message);
    }
}
