package com.bcbsm.filemgt.exception;

public class FileMgtException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileMgtException() {
		super();
	}

	public FileMgtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileMgtException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileMgtException(String message) {
		super(message);
	}

	public FileMgtException(Throwable cause) {
		super(cause);
	}

}
