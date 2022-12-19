package inmobiliapi.repository;

import inmobiliapi.model.Contrato;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ContratoRepository extends MongoRepository<Contrato, String>{
    
    public List<Contrato> findByPropietario(String email);
    public List<Contrato> findByArrendatario(String email);
    public Contrato findByIdContrato(Long id);
   
}
