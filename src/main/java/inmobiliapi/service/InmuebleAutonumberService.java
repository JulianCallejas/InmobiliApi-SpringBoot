
package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceAPI;
import inmobiliapi.model.InmuebleAutonumber;


public interface InmuebleAutonumberService extends GenericServiceAPI<InmuebleAutonumber, Long>{
    
    public Long getAutonumber();
    
}
