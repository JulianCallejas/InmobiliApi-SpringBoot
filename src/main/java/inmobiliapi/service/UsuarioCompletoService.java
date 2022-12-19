
package inmobiliapi.service;


import inmobiliapi.model.UsuarioCompleto;


public interface UsuarioCompletoService {
    
    public UsuarioCompleto getByEmail(String email);
    
}
