package inmobiliapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "contratos")
public class Contrato {

    @Id
    private String id;

    @Schema(example = "11", description = "")
    @Indexed(name = "idContrato_1", unique = true, direction = IndexDirection.ASCENDING)
    private Long idContrato;
    
    @Schema(example = "1", description = "")
    @Indexed(name = "idInmueble_1", unique = false, direction = IndexDirection.ASCENDING)
    private Long idInmueble;

    @Schema(example = "2022-10-12", description = "")
    private String fechaContrato;
    
    @Schema(example = "2022-10-01", description = "")
    private String fechaPublicacion;
    
    @Schema(example = "usuario1@correo.com", description = "")
    @Indexed(name = "propietario_1", unique = false, direction = IndexDirection.ASCENDING)
    private String propietario;
    
    @Schema(example = "prueba@correo.com", description = "")
    @Indexed(name = "arrendatario_1", unique = false, direction = IndexDirection.ASCENDING)
    private String arrendatario;
    
    @Schema(example = "12", description = "Duración del contrato en meses")
    private int duracion;
    
    @Schema(example = "1000000", description = "")
    private double valorArriendo;
    
    @Schema(example = "150000", description = "")
    private double valorAdmin;
    
    @Schema(example = "true", description = "")
    private boolean activo;
    
    @Schema(example = "2030-10-11", description = "")
    private String fechaTerminacion;

    public boolean camposRequeridos() {
        return !((this.fechaContrato == null || this.fechaContrato.isBlank())
                || (this.fechaPublicacion == null || this.fechaPublicacion.isBlank())
                || (this.fechaPublicacion == null || this.fechaPublicacion.isBlank())
                || (this.propietario == null || this.propietario.isBlank() || !ValidEmail.validEmail(this.propietario))
                || (this.arrendatario == null || this.arrendatario.isBlank() || !ValidEmail.validEmail(this.arrendatario))
                || (this.duracion == 0)
                || (this.valorArriendo == 0));
    }

    public List<String> camposFaltantes() {

        List<String> camposFaltantes = new ArrayList<>();
        
        if (this.fechaContrato == null || this.fechaContrato.isBlank()) {camposFaltantes.add("Debe ingresar la fecha del contrato");}
        if (this.fechaPublicacion == null || this.fechaPublicacion.isBlank()) {camposFaltantes.add("Debe ingresar la fecha de publicacion del inmueble");}
        if (this.propietario == null || this.propietario.isBlank() || !ValidEmail.validEmail(this.propietario)) {camposFaltantes.add("propietario debe ser email");}
        if (this.arrendatario == null || this.arrendatario.isBlank() || !ValidEmail.validEmail(this.arrendatario)) {camposFaltantes.add("arrendatario debe ser email");}
        if (this.duracion == 0) {camposFaltantes.add("duracion debe ser un numero entero mayor a 0");}
        if (this.valorArriendo == 0) {camposFaltantes.add("valorArriendo debe ser un numero entero mayor a 0");}

        return camposFaltantes;

    }
}
