package ar.com.ada.backend12.carRental.util;

public class ApiMessage implements ApiReturnable{
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
