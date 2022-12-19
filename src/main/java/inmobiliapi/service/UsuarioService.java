
package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceAPI;
import inmobiliapi.model.Usuario;


public interface UsuarioService extends GenericServiceAPI<Usuario, String>{
    
    public Usuario getByEmail(String email);
    
}
