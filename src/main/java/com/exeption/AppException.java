package com.exeption;

public abstract class AppException extends RuntimeException{

	public AppException(Throwable throwable) {
        super(throwable);
    }
}
