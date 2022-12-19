
package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceImpl;
import inmobiliapi.model.Usuario;
import inmobiliapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, String> implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepo;
    

    @Override
    public CrudRepository<Usuario, String> getDao() {
        return usuarioRepo;
    }

    @Override
    public Usuario getByEmail(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email);
        return usuario;
    }
    
}
