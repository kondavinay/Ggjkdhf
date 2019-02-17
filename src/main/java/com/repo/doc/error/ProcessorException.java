package com.repo.doc.error;

public class ProcessorException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;

    public ProcessorException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}