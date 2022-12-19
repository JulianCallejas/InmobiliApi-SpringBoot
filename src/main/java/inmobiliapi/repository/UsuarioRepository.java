
package inmobiliapi.repository;

import inmobiliapi.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    
    public Usuario findByEmail(String email);
    
}
