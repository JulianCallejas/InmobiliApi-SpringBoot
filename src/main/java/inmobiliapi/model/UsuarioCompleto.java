
package inmobiliapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(value = "usuariosCompleto")
public class UsuarioCompleto {
    @Id
    private String id;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Schema(example = "prueba@correo.com", description = "")
    private String email;
    
    @Schema(example = "Pedro Perez", description = "")
    private String nombre;
    
    @Schema(example = "120120120", description = "")
    private String identificacion;
    
    @Schema(example = "2411111", description = "")
    private String telefono;
    private List<MiInmueble> misInmuebles;
    private List<MiArriendo> misArriendos;
   

   
    
    

}
