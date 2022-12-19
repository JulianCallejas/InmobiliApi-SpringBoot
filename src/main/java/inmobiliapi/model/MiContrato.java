
package inmobiliapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;


public class MiContrato {
    
    private String id;
    
    @Schema(example = "15", description = "")
    private Long idContrato;
    
    @Schema(example = "2", description = "")
    private Long idInmueble;
    
    @Schema(example = "2022-10-12", description = "")
    private String fechaContrato;
    
    @Schema(example = "2022-10-02", description = "")
    private String fechaPublicacion;
    
    private List<Individuo> arrendatario;
    
    @Schema(example = "12", description = "Duración del contrato en meses")
    private int duracion;
    
    @Schema(example = "1000000", description = "")
    private double valorArriendo;
    
    @Schema(example = "100000", description = "")
    private double valorAdmin;
    
    @Schema(example = "true", description = "")
    private boolean activo;
    
    @Schema(example = "2030-10-11", description = "")
    private String fechaTerminacion;
    

    public MiContrato() {
    }

    public MiContrato(String id, Long idContrato, Long idInmueble, String fechaContrato, String fechaPublicacion, List<Individuo> arrendatario, int duracion, double valorArriendo, double valorAdmin, boolean activo, String fechaTerminacion) {
        this.id = id;
        this.idContrato = idContrato;
        this.idInmueble = idInmueble;
        this.fechaContrato = fechaContrato;
        this.fechaPublicacion = fechaPublicacion;
        this.arrendatario = arrendatario;
        this.duracion = duracion;
        this.valorArriendo = valorArriendo;
        this.valorAdmin = valorAdmin;
        this.activo = activo;
        this.fechaTerminacion = fechaTerminacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public Long getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Long idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(String fechaContrato) {
        this.fechaContrato = fechaContrato;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public double getValorArriendo() {
        return valorArriendo;
    }

    public void setValorArriendo(double valorArriendo) {
        this.valorArriendo = valorArriendo;
    }

    public double getValorAdmin() {
        return valorAdmin;
    }

    public void setValorAdmin(double valorAdmin) {
        this.valorAdmin = valorAdmin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(String fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

        
}
