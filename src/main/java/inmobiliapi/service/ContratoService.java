
package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceAPI;
import inmobiliapi.model.Contrato;
import java.util.List;


public interface ContratoService extends GenericServiceAPI<Contrato, String>{
    
    public Contrato getByIdContrato(Long idContrato);
    public List<Contrato> getContratosByPropietario(String email);
    public List<Contrato> getContratosByArrendatario(String email);
    
}
