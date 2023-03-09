package ar.com.ada.backend12.carRental.util.api.message;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;

public class ApiMessage implements ApiReturnable {
    private String message;

    public ApiMessage() {
    }

    public ApiMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
