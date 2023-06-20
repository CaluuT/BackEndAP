package com.Portfolio.CaluT.Servicios;

import com.Portfolio.CaluT.Entidades.Hys;
import com.Portfolio.CaluT.Repositorio.RepositorioHys;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicioHys {
    @Autowired
    RepositorioHys repositorioHys;
    
    public List<Hys> list(){
        return repositorioHys.findAll();
    }
    
    public Optional<Hys> getOne(int id){
        return repositorioHys.findById(id);
    }
    
    public Optional<Hys> getByNombre(String nombre){
        return repositorioHys.findByNombre(nombre);
    }
    
    public void save(Hys skills){
        repositorioHys.save(skills);
    }
    
    public void delete(int id){
        repositorioHys.deleteById(id);
    }
    
    public boolean existsById(int id){
        return  repositorioHys.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return repositorioHys.existsByNombre(nombre);
    }
}
