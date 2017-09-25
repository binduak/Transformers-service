package com.tw.exception;

public abstract class BaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private int errorCode;
    
    // Constructors -------------------------------------------------------------------------------

    /**
     * Constructs a BaseException with the given detail message.
     * @param message The detail message of the BaseException.
     */
    public BaseException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Constructs a BaseException with the given root cause.
     * @param cause The root cause of the BaseException.
     */
    public BaseException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * Constructs a BaseException with the given detail message and root cause.
     * @param message The detail message of the BaseException.
     * @param cause The root cause of the BaseException.
     */
    public BaseException(String message, int errorCode,Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

	public int getErrorCode() {
		return errorCode;
	}
}
