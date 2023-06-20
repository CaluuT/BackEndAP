package com.Portfolio.CaluT.Controladores;

import com.Portfolio.CaluT.DTO.PersonaDTO;
import com.Portfolio.CaluT.Entidades.Persona;
import com.Portfolio.CaluT.Seguridad.Controlador.Mensaje;
import com.Portfolio.CaluT.Servicios.ImpServicioPersona;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://frontendap-30130.web.app","http://localhost:4200"})
public class ControladorPersona {
    @Autowired ImpServicioPersona iservicioPersona;
        
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = iservicioPersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PersonaDTO perdto){
        if(!iservicioPersona.existsById(id))
            return new ResponseEntity(new Mensaje ("ID no existente"), HttpStatus.BAD_REQUEST);
        if (iservicioPersona.existsByNombre(perdto.getNombre()) && iservicioPersona.getByNombre(perdto.getNombre()).get().getId() !=id)
            return new ResponseEntity(new Mensaje ("Persona existente"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(perdto.getNombre()))
            return new ResponseEntity(new Mensaje ("Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Persona persona = iservicioPersona.getOne(id).get();
        persona.setNombre(perdto.getNombre());
        persona.setApellido(perdto.getApellido());
        persona.setDescripcion(perdto.getDescripcion());
        persona.setImg(perdto.getImg());
        iservicioPersona.save(persona);
        return new ResponseEntity (new Mensaje ("Persona actualizada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!iservicioPersona.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el ID"), HttpStatus.NOT_FOUND);
        Persona persona = iservicioPersona.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
}
