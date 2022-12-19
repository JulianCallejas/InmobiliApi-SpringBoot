
package inmobiliapi.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthCredentials {
    
    @Schema(example = "usuario1@correo.com", description = "email")
    private String email;
    @Schema(example = "1234", description = "password")
    private String contrasena;

}
