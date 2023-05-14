package com.Portfolio.CaluT.Controladores;

import com.Portfolio.CaluT.Entidades.Persona;
import com.Portfolio.CaluT.Interfaces.IServicioPersona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorPersona {
    @Autowired IServicioPersona iservicioPersona;
    
    @GetMapping("personas/traer")
    public List<Persona> getPersona(){
        return iservicioPersona.getPersona();
    }
    
    
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
     iservicioPersona.savePersona(persona);
     return "Persona creada correctamente";
    }
    
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        iservicioPersona.deletePersona(id);
        return "Persona eliminada correctamente";
    }
    
    
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam("nombre") String nuevoNombre,
                               @RequestParam("apellido") String nuevoApellido,
                               @RequestParam("img") String nuevoImg){
    Persona persona = iservicioPersona.findPersona(id);
    
    persona.setNombre(nuevoNombre);
    persona.setApellido(nuevoApellido);
    persona.setImg(nuevoImg);
    
    iservicioPersona.savePersona(persona);
    return persona;
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return iservicioPersona.findPersona((long)1);
    }
    
}
