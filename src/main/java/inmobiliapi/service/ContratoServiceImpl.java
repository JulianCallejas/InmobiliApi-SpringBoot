
package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceImpl;
import inmobiliapi.model.Contrato;
import inmobiliapi.repository.ContratoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ContratoServiceImpl extends GenericServiceImpl<Contrato, String> implements ContratoService{
    
    @Autowired
    private ContratoRepository contratoRepo;

    @Override
    public CrudRepository<Contrato, String> getDao() {
        return contratoRepo;
    }

    @Override
    public Contrato getByIdContrato(Long idContrato) {
        Contrato contrato = contratoRepo.findByIdContrato(idContrato);
        return contrato;
    }

    @Override
    public List<Contrato> getContratosByPropietario(String email) {
        List<Contrato> contratos = contratoRepo.findByPropietario(email);
        return contratos;
    }

    @Override
    public List<Contrato> getContratosByArrendatario(String email) {
        List<Contrato> contratos = contratoRepo.findByArrendatario(email);
        return contratos;
    }
    
}
