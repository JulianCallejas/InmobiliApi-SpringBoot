
package inmobiliapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(value = "contratos")
public class ContratoAutonumber {
    
    @Indexed(name = "idContrato_1", unique = true, direction = IndexDirection.ASCENDING)
    private Long idContrato;
    
}
