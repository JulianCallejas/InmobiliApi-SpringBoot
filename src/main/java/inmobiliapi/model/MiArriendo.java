
package inmobiliapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class MiArriendo {

    private String id;

    @Schema(example = "1", description = "")
    private Long idInmueble;

    private List<Individuo> propietario;
    
    @Schema(example = "Apartamento en cali", description = "")
    private String titulo;
    
    @Schema(example = "Arriendo apartamento amplio", description = "")
    private String descripcion;
    
    private Especificaciones especificaciones;
    
    @Schema(example = "Publicado", description = "")
    private String estadoPublicacion;
    
    @Schema(example = "2022-10-12", description = "")
    private String fechaPublicacion;

    String[] fotos = new String[16];
    private List<MiContrato> contratos;

    public MiArriendo() {
    }

    public MiArriendo(String id, Long idInmueble, List<Individuo> propietario, String titulo, String descripcion, Especificaciones especificaciones, String estadoPublicacion, String fechaPublicacion, List<MiContrato> contratos) {
        this.id = id;
        this.idInmueble = idInmueble;
        this.propietario = propietario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.especificaciones = especificaciones;
        this.estadoPublicacion = estadoPublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.contratos = contratos;
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

    public List<Individuo> getPropietario() {
        return propietario;
    }

    public void setPropietario(List<Individuo> propietario) {
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

    public String[] getFotos() {
        return fotos;
    }

    public void setFotos(String[] fotos) {
        this.fotos = fotos;
    }

    public List<MiContrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<MiContrato> contratos) {
        this.contratos = contratos;
    }

        
}
