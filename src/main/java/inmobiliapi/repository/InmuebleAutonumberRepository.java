
package inmobiliapi.repository;


import inmobiliapi.model.InmuebleAutonumber;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface InmuebleAutonumberRepository extends MongoRepository<InmuebleAutonumber, Long>{
    
}
