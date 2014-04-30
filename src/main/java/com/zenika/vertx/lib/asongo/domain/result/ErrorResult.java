package com.zenika.vertx.lib.asongo.domain.result;

/**
 * This class permit to get and error from the mongo persistor
 * Is the same contract for any operation
 * @author M. Labusquière
 */
public abstract class ErrorResult {
	/**
	 * Status should be OK, if there is no error
	 */
	private String status;
	/**
	 * The error message
	 */
	private String Message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		this.Message = message;
	}


	public boolean isNotError() {
		if(status.equals("error"))
			return false;
		return true;
	}

}
