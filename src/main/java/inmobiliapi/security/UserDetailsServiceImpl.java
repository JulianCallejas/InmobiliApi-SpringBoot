package inmobiliapi.security;

import inmobiliapi.model.Usuario;
import inmobiliapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        try {
            Usuario usuario = usuarioRepo.findByEmail(email);
            if (usuario == null) {
                //throw new UsernameNotFoundException("User not found");
                usuario = new Usuario("Usernotfound","Usernotfound","$2a$10$mLCz.Eo3od82ntXiw3tr7exRIJWTWpN9vvSzl5.A3mbql7b4pqpk.","Usernotfound","Usernotfound","Usernotfound");
            }
            return new UserDetailsImpl(usuario);
        } catch (Exception e) {
            System.out.println("Error al cargar usuario: "+e.toString());
            Usuario usuario = new Usuario("Usernotfound","Usernotfound","$2a$10$mLCz.Eo3od82ntXiw3tr7exRIJWTWpN9vvSzl5.A3mbql7b4pqpk.","Usernotfound","Usernotfound","Usernotfound");
            usuario.setEmail("Usernotfound");
            usuario.setContrasena("$2a$10$mLCz.Eo3od82ntXiw3tr7exRIJWTWpN9vvSzl5.A3mbql7b4pqpk.");
            
            return new UserDetailsImpl(usuario);
        }
    }
}
