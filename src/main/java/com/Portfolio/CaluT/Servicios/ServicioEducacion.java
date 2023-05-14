package com.Portfolio.CaluT.Servicios;

import com.Portfolio.CaluT.Entidades.Educacion;
import com.Portfolio.CaluT.Repositorio.RepositorioEducacion;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicioEducacion {
    @Autowired
    RepositorioEducacion repositorioEducacion;
    
    public List<Educacion> list(){
        return repositorioEducacion.findAll();
    }
    
    public Optional<Educacion> getOne(int id){
        return repositorioEducacion.findById(id);
    }
    
    public Optional<Educacion> getByNombreE(String nombreE){
        return repositorioEducacion.findByNombreE(nombreE);
    }
    
    public void save(Educacion educacion){
        repositorioEducacion.save(educacion);
    }
    
    public void delete(int id){
        repositorioEducacion.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repositorioEducacion.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return repositorioEducacion.existsByNombreE(nombreE);
    }
}
