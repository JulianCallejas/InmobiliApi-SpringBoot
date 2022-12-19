package inmobiliapi.service;

import inmobiliapi.commonservice.GenericServiceImpl;
import inmobiliapi.model.Inmueble;
import inmobiliapi.repository.InmuebleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class InmuebleServiceImpl extends GenericServiceImpl<Inmueble, String> implements InmuebleService {

    @Autowired
    private InmuebleRepository inmuebleRepo;

    @Override
    public CrudRepository<Inmueble, String> getDao() {
        return inmuebleRepo;
    }

    @Override
    public Inmueble getByIdInmueble(Long idInmueble) {
        Inmueble inmueble = inmuebleRepo.findByIdInmueble(idInmueble);
        return inmueble;
    }

    @Override
    public List<Inmueble> getAvailableInmuebles() {

        List<Inmueble> inmueblesDisponibles = inmuebleRepo.findAvailableInmuebles();

        return inmueblesDisponibles;

    }

    @Override
    public List<Inmueble> getInmueblesByPropietario(String email) {
        List<Inmueble> inmueblesPropietario = inmuebleRepo.findByPropietario(email);
        return inmueblesPropietario;
    }

    @Override
    public List<Inmueble> getInmueblesByArrendatario(String email) {
        List<Inmueble> inmueblesArrendatario = inmuebleRepo.findByArrendatario(email);
        return inmueblesArrendatario;
    }

   

}
