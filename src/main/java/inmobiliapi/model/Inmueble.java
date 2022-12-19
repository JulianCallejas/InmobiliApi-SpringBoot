package inmobiliapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "inmuebles")
public class Inmueble {

    @Id
    private String id;

    @Indexed(name = "idInmueble_1", unique = true, direction = IndexDirection.ASCENDING)
    @Schema(example = "14", description = "")
    private Long idInmueble;

    @Indexed(name = "propietario_1", direction = IndexDirection.ASCENDING)
    @Email
    @Schema(example = "prueba@correo.com", description = "")
    private String propietario;
    
    @Schema(example = "Apartamento en cali", description = "")
    private String titulo;
    
    @Schema(example = "Arriendo apartamento amplio", description = "")
    private String descripcion;
        
    private Especificaciones especificaciones;
    
    @Schema(example = "Publicado", description = "")
    private String estadoPublicacion;
    
    @Schema(example = "2022-10-12", description = "")
    private String fechaPublicacion;

    @Indexed(name = "arrendatario_1", direction = IndexDirection.ASCENDING)
    @Email
    @Schema(example = "usuario1@correo.com", description = "")
    private String arrendatario;
    String[] fotos = new String[16];

    public Inmueble() {
    }

    public Inmueble(Long idInmueble, String propietario, String titulo, String descripcion, Especificaciones especificaciones, String estadoPublicacion, String fechaPublicacion) {
        this.idInmueble = idInmueble;
        this.propietario = propietario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.especificaciones = especificaciones;
        this.estadoPublicacion = estadoPublicacion;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Inmueble(String id, Long idInmueble, String propietario, String titulo, String descripcion, Especificaciones especificaciones, String estadoPublicacion, String fechaPublicacion, String arrendatario) {
        this.id = id;
        this.idInmueble = idInmueble;
        this.propietario = propietario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.especificaciones = especificaciones;
        this.estadoPublicacion = estadoPublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.arrendatario = arrendatario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Long idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Especificaciones getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(Especificaciones especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(String estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(String arrendatario) {
        this.arrendatario = arrendatario;
    }

    public String[] getFotos() {
        return fotos;
    }

    public void setFotos(String[] fotos) {
        this.fotos = fotos;
    }

    public boolean camposRequeridos() {
        return !((this.propietario == null || this.propietario.isBlank()||!ValidEmail.validEmail(this.propietario))
                || (this.titulo == null || this.titulo.isBlank())
                || (this.estadoPublicacion == null || this.estadoPublicacion.isBlank())
                || (this.fechaPublicacion == null || this.fechaPublicacion.isBlank())
                || (this.especificaciones.getCiudad() == null || this.especificaciones.getCiudad().isBlank())
                || (this.especificaciones.getDireccion() == null || this.especificaciones.getDireccion().isBlank())
                || (this.especificaciones.getValorArriendo() == 0));
    }

    public List<String> camposFaltantes() {

        List<String> camposFaltantes = new ArrayList<>();

        if (this.propietario == null || this.propietario.isBlank()||!ValidEmail.validEmail(this.propietario)) {camposFaltantes.add( "propietario debe ser email");}
        if (this.titulo == null || this.titulo.isBlank()) {camposFaltantes.add("titulo es requerido");}
        if (this.estadoPublicacion == null || this.estadoPublicacion.isBlank()) {camposFaltantes.add( "estadoPublicacion requerido");}
        if (this.fechaPublicacion == null || this.fechaPublicacion.isBlank()) {camposFaltantes.add("fechaPublicacion debe ser fecha");}
        if (this.especificaciones.getCiudad() == null || this.especificaciones.getCiudad().isBlank()) {camposFaltantes.add( "ciudad requerida");}
        if (this.especificaciones.getDireccion() == null || this.especificaciones.getDireccion().isBlank()) {camposFaltantes.add( "direccion requerida");}
        if (this.especificaciones.getValorArriendo() == 0) {camposFaltantes.add("valorArriendo debe ser mayor a 0");}
        
        return camposFaltantes;
        

    }

}
