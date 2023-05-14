package com.Portfolio.CaluT.Servicios;

import com.Portfolio.CaluT.Entidades.Persona;
import com.Portfolio.CaluT.Repositorio.IRepositorioPersona;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpServicioPersona{
    @Autowired
    IRepositorioPersona irepositorioPersona;
    
    
    public List<Persona> list(){
    return irepositorioPersona.findAll();
    }
    
    public Optional<Persona> getOne(int id){
        return irepositorioPersona.findById(id);
    }
    
    public Optional<Persona> getByNombre(String nombre){
        return irepositorioPersona.findByNombre(nombre);   
    }
    
    public void save(Persona persona){
        irepositorioPersona.save(persona);
    }
    
    public void delete(int id){
        irepositorioPersona.deleteById(id);
    }
    
    public boolean existsById(int id){
        return irepositorioPersona.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return irepositorioPersona.existsByNombre(nombre);
    }
    
}
