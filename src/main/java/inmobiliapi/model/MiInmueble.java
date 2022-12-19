
package inmobiliapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;


public class MiInmueble {
    
    private String _id;
    
    @Schema(example = "1", description = "")
    private Long idInmueble;
    
    @Schema(example = "Apartamento en cali", description = "")
    private String titulo;
    
    @Schema(example = "Arriendo apartamento amplio", description = "")
    private String descripcion;
    
    private Especificaciones especificaciones;
    
    @Schema(example = "Publicado", description = "")
    private String estadoPublicacion;
    
    @Schema(example = "2022-10-12", description = "")
    private String fechaPublicacion;

    private List<Individuo> arrendatario;
    String[] fotos = new String[16];
    
    private List<MiContrato> contratos;

    public MiInmueble() {
    }

    public MiInmueble(String _id, Long idInmueble, String titulo, String descripcion, Especificaciones especificaciones, String estadoPublicacion, String fechaPublicacion, List<Individuo> arrendatario, List<MiContrato> contratos) {
        this._id = _id;
        this.idInmueble = idInmueble;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.especificaciones = especificaciones;
        this.estadoPublicacion = estadoPublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.arrendatario = arrendatario;
        this.contratos = contratos;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public Long getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Long idInmueble) {
        this.idInmueble = idInmueble;
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

    public List<Individuo> getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(List<Individuo> arrendatario) {
        this.arrendatario = arrendatario;
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
