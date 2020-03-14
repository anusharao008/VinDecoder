package com.altimetrik.workshop.vin.decoder.error;

import org.springframework.http.HttpStatus;

public class VinException extends RuntimeException {
    // status code 400, 500 etc..
    private HttpStatus httpStatus;
    private String errorText;
    private String errorCode;

    public VinException() {
        super();
    }

    public VinException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorText = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public String toString() {
        String message = "";
        if(this.errorCode != null ) {
            message +=  "error code : " + this.errorCode + "\n";
        }
        return (message + "error text : " + this.errorText);
    }
}
