package com.Portfolio.CaluT.Controladores;

import com.Portfolio.CaluT.DTO.ExperienciaDTO;
import com.Portfolio.CaluT.Entidades.Experiencia;
import com.Portfolio.CaluT.Seguridad.Controlador.Mensaje;
import com.Portfolio.CaluT.Servicios.ServicioExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/explab")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorExperiencia {
    @Autowired
    ServicioExperiencia servicioExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = servicioExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienciaDTO expdto){
        if(StringUtils.isBlank(expdto.getNombreE()))
            return new ResponseEntity(new Mensaje("Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(servicioExperiencia.existsByNombreE(expdto.getNombreE()))
            return new ResponseEntity(new Mensaje("Experiencia ya existente"), HttpStatus.BAD_REQUEST);
        Experiencia experiencia = new Experiencia(expdto.getNombreE(), expdto.getDescripcionE());
        servicioExperiencia.save(experiencia);
        return new ResponseEntity (new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienciaDTO expdto){
        if(!servicioExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje ("ID no existente"), HttpStatus.BAD_REQUEST);
        if (servicioExperiencia.existsByNombreE(expdto.getNombreE()) && servicioExperiencia.getByNombreE(expdto.getNombreE()).get().getId() !=id)
            return new ResponseEntity(new Mensaje ("Experiencia existente"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(expdto.getNombreE()))
            return new ResponseEntity(new Mensaje ("Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        Experiencia experiencia = servicioExperiencia.getOne(id).get();
        experiencia.setNombreE(expdto.getNombreE());
        experiencia.setDescripcionE(expdto.getDescripcionE());
        servicioExperiencia.save(experiencia);
        return new ResponseEntity (new Mensaje ("Experiencia actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!servicioExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje ("ID no existente"), HttpStatus.BAD_REQUEST);
        servicioExperiencia.delete(id);
        return new ResponseEntity(new Mensaje ("Experiencia Eliminada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!servicioExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = servicioExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
}
