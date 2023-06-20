package com.Portfolio.CaluT.Servicios;

import com.Portfolio.CaluT.Entidades.Experiencia;
import com.Portfolio.CaluT.Repositorio.RepositorioExperiencia;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicioExperiencia {
    @Autowired
    RepositorioExperiencia repositorioExperiencia;
    
    public List<Experiencia> list(){
        return repositorioExperiencia.findAll();
    }
    
    public Optional<Experiencia> getOne(int id){
        return repositorioExperiencia.findById(id);
    }
    
    public Optional<Experiencia> getByNombreE(String nombreE){
        return repositorioExperiencia.findByNombreE(nombreE);   
    }
    
    public void save(Experiencia experiencia){
        repositorioExperiencia.save(experiencia);
    }
    
    public void delete(int id){
        repositorioExperiencia.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repositorioExperiencia.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return repositorioExperiencia.existsByNombreE(nombreE);
    }
    
}
