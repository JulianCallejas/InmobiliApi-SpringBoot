package inmobiliapi.repository;

import inmobiliapi.model.Inmueble;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface InmuebleRepository extends MongoRepository<Inmueble, String> {

    public Inmueble findByIdInmueble(Long idInmueble);

    @Query("{arrendatario: '' }")
    public List<Inmueble> findAvailableInmuebles();
    
    public List<Inmueble> findByPropietario(String email);
    
    public List<Inmueble> findByArrendatario(String email);
    
}
