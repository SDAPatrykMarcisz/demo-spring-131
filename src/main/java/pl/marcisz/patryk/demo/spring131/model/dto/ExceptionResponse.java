package pl.marcisz.patryk.demo.spring131.model.dto;

public class ExceptionResponse {
    private String errorMessage;

    public ExceptionResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
