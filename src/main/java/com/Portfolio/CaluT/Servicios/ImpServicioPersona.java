package com.Portfolio.CaluT.Servicios;

import com.Portfolio.CaluT.Entidades.Persona;
import com.Portfolio.CaluT.Interfaces.IServicioPersona;
import com.Portfolio.CaluT.Repositorio.IRepositorioPersona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpServicioPersona implements IServicioPersona{
    @Autowired IRepositorioPersona irepositorioPersona;
    
    
    @Override
    public List<Persona> getPersona() {
        List<Persona> persona = irepositorioPersona.findAll();
        return persona;
    }

    @Override
    public void savePersona(Persona persona) {
        irepositorioPersona.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        irepositorioPersona.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona persona = irepositorioPersona.findById(id).orElse(null);
        return persona;
    }
    
}
