
package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceImpl;
import inmobiliapi.model.InmuebleAutonumber;
import inmobiliapi.repository.InmuebleAutonumberRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class InmuebleAutonumberServiceImpl extends GenericServiceImpl<InmuebleAutonumber, Long> implements InmuebleAutonumberService{

    @Autowired
    InmuebleAutonumberRepository inmuebleAutonumberRepo;
    
    @Override
    public CrudRepository<InmuebleAutonumber, Long> getDao() {
        return inmuebleAutonumberRepo;
    }
    
    public Long getAutonumber(){
        
        List<InmuebleAutonumber> inmuebleAutonumber =  inmuebleAutonumberRepo.findAll(Sort.by(Sort.Direction.DESC, "idInmueble"));
        Long autoNumber = inmuebleAutonumber.get(0).getIdInmueble();
        autoNumber += 1;
        return autoNumber;
    
    }
    
}
