
package inmobiliapi.security;

public class TokenBodyResponse {

    private String access_token;
    private final int status = 200;

    public TokenBodyResponse(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getStatus() {
        return status;
    }

      

    
}
