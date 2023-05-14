package com.Portfolio.CaluT.Interfaces;

import com.Portfolio.CaluT.Entidades.Persona;
import java.util.List;

public interface IServicioPersona {
    
   public List<Persona> getPersona();
           
    
   public void savePersona(Persona persona);
   
   
   public void deletePersona(Long id);
   
   
   public Persona findPersona(Long id);
}
