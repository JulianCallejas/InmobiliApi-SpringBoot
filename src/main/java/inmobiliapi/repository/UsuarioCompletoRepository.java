package inmobiliapi.repository;

import inmobiliapi.model.UsuarioCompleto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioCompletoRepository extends MongoRepository<UsuarioCompleto, String> {

    public UsuarioCompleto findByEmail(String email);

}
