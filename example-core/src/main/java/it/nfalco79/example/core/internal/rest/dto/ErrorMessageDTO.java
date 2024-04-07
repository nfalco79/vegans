package it.nfalco79.example.core.internal.rest.dto;

public class ErrorMessageDTO {

    private final String code;
    private final String message;

    public ErrorMessageDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
