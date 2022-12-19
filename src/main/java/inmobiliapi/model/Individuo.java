
package inmobiliapi.model;

import io.swagger.v3.oas.annotations.media.Schema;



public class Individuo {

    private String _id;

    @Schema(example = "prueba@correo.com", description = "")
    private String email;

    @Schema(example = "Pedro Perez", description = "")
    private String nombre;
    
    @Schema(example = "120120120", description = "")
    private String identificacion;
    
    @Schema(example = "2411111", description = "")
    private String telefono;

    public Individuo() {
    }

    public Individuo(String _id, String email, String nombre, String identificacion, String telefono) {
        this._id = _id;
        this.email = email;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    
    
    
}
