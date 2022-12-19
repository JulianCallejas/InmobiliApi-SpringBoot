package inmobiliapi.model;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "usuarios")
public class Usuario {

    @Id
    private String id;

    @Indexed(name = "email_1", unique = true, direction = IndexDirection.ASCENDING)
    @Size(max = 100)
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Schema(example = "prueba@correo.com", description = "")
    private String email;
    
    @Schema(example = "1234", description = "")
    private String contrasena;
    
    @Schema(example = "Pedro Perez", description = "")
    private String nombre;
    
    @Schema(example = "120120120", description = "")
    private String identificacion;
    
    @Schema(example = "2411111", description = "")
    private String telefono;

    public Usuario() {

    }

    public Usuario(String id, String email, String contrasena, String nombre, String identificacion, String telefono) {
        this.id = id;
        this.email = email;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "{ 'email': '" + email + "', 'nombre': '" + nombre + "', 'identificacion': '" + identificacion + "', 'telefono': '" + telefono + "'}";
    }

    public boolean validEmail() {
        return ValidEmail.validEmail(this.email);

    }

}
