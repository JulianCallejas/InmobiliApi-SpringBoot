
package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceImpl;
import inmobiliapi.model.ContratoAutonumber;
import inmobiliapi.repository.ContratoAutonumberRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class ContratoAutonumberServiceImpl extends GenericServiceImpl<ContratoAutonumber, Long> implements ContratoAutonumberService{
    
    @Autowired
    ContratoAutonumberRepository contratoAutonumberRepo;

    @Override
    public CrudRepository<ContratoAutonumber, Long> getDao() {
        return contratoAutonumberRepo;
    }

    @Override
    public Long getAutonumber() {
        
        List<ContratoAutonumber> contratoAutonumber =  contratoAutonumberRepo.findAll(Sort.by(Sort.Direction.DESC, "idContrato"));
        Long autoNumber = contratoAutonumber.get(0).getIdContrato();
        autoNumber += 1;
        return autoNumber;
        
    }
    
   
    
}
