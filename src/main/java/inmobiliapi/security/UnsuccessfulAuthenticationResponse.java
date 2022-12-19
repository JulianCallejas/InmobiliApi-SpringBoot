
package inmobiliapi.security;


public class UnsuccessfulAuthenticationResponse {
    private final long statusCode = 400;
    private final String message = "Credenciales incorrectas";

    public UnsuccessfulAuthenticationResponse() {
    }

    public long getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
    
}
