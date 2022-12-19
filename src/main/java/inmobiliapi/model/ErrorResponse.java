package inmobiliapi.model;

import lombok.Data;

@Data
public class ErrorResponse {

    private int statusCode;
    private Object message;
    private String error;

    public ErrorResponse() {
    }

    public ErrorResponse(int statusCode) {
        this.statusCode = statusCode;

        switch (statusCode) {
            case 400:
                this.message = "Datos Incorrectos";
                this.error = "Bad Request";
                break;

            case 401:
                this.message = "No tiene Autorizacion";
                this.error = "Unauthorized";
                break;
            
            case 500:
                this.message = "Server error";
                this.error = "Internal server error";
                break;
            
            default:
                break;
        }

    }

    public ErrorResponse(int statusCode, Object message, String error) {
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
    }

}
