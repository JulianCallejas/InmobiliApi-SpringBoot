
package inmobiliapi.service;

import inmobiliapi.model.UsuarioCompleto;
import inmobiliapi.repository.UsuarioCompletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioCompletoServiceImpl implements UsuarioCompletoService{
    
    @Autowired
    private UsuarioCompletoRepository usuarioCompletoRepo;

    @Override
    public UsuarioCompleto getByEmail(String email) {
        
        UsuarioCompleto usuarioCompleto = usuarioCompletoRepo.findByEmail(email);
        return usuarioCompleto;
        
    }
    
}
