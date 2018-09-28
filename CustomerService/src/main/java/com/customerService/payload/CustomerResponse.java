package com.customerService.payload;

public class CustomerResponse {
	private String id;
    private Boolean success;
    private String message;

    public CustomerResponse(Boolean success, String message, String id) {
        this.id = id;
    	this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}