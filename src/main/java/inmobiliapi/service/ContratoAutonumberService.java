
package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceAPI;
import inmobiliapi.model.ContratoAutonumber;


public interface ContratoAutonumberService extends GenericServiceAPI<ContratoAutonumber, Long>{
    
    public Long getAutonumber();
    
}
