
package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceAPI;
import inmobiliapi.model.Inmueble;
import java.util.List;



public interface InmuebleService extends GenericServiceAPI<Inmueble, String>{
    
    public Inmueble getByIdInmueble(Long idInmueble);
    public List<Inmueble> getAvailableInmuebles();
    public List<Inmueble> getInmueblesByPropietario(String email);
    public List<Inmueble> getInmueblesByArrendatario(String email);
    
    
}
