package com.tw.exception;

public class DAOException extends BaseException {

	// Constants ----------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;
    
    // Constructors -------------------------------------------------------------------------------

    /**
     * Constructs a DAOException with the given detail message.
     * @param message The detail message of the DAOException.
     */
    public DAOException(String message, int errorCode) {
        super(message,errorCode);
    }

    /**
     * Constructs a DAOException with the given root cause.
     * @param cause The root cause of the DAOException.
     */
    public DAOException(Throwable cause, int errorCode) {
        super(cause, errorCode);
    }

    /**
     * Constructs a DAOException with the given detail message and root cause.
     * @param message The detail message of the DAOException.
     * @param cause The root cause of the DAOException.
     */
    public DAOException(String message, int errorCode,Throwable cause) {
        super(message,errorCode, cause);
    }
}
