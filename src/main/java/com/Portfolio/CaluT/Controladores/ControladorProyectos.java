package com.Portfolio.CaluT.Controladores;

import com.Portfolio.CaluT.DTO.ProyectosDTO;
import com.Portfolio.CaluT.Entidades.Proyectos;
import com.Portfolio.CaluT.Seguridad.Controlador.Mensaje;
import com.Portfolio.CaluT.Servicios.ServicioProyectos;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://frontendap-30130.web.app"})
public class ControladorProyectos {
    @Autowired 
    ServicioProyectos servicioProyectos;
        
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = servicioProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProyectosDTO proyectodto){
        if(!servicioProyectos.existsById(id))
            return new ResponseEntity(new Mensaje ("ID no existente"), HttpStatus.BAD_REQUEST);
        if(servicioProyectos.existsByNombreP(proyectodto.getNombreP()) && servicioProyectos.getByNombreP(proyectodto.getNombreP()).get().getId() !=id)
            return new ResponseEntity(new Mensaje ("Proyecto existente"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(proyectodto.getNombreP()))
            return new ResponseEntity(new Mensaje ("Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Proyectos proyectos = servicioProyectos.getOne(id).get();
        proyectos.setNombreP(proyectodto.getNombreP());
        proyectos.setDescripcionP(proyectodto.getDescripcionP());
        proyectos.setImgP(proyectodto.getImgP());
        proyectos.setUrlP(proyectodto.getUrlP());
        servicioProyectos.save(proyectos);
        return new ResponseEntity (new Mensaje ("Proyecto actualizado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProyectosDTO proyectodto){
        if(StringUtils.isBlank(proyectodto.getNombreP()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(servicioProyectos.existsByNombreP(proyectodto.getNombreP()))
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        
        Proyectos proyectos = new Proyectos(proyectodto.getNombreP(), proyectodto.getDescripcionP(), proyectodto.getImgP(), proyectodto.getUrlP());
        servicioProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto creado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!servicioProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        servicioProyectos.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if(!servicioProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("no existe el ID"), HttpStatus.NOT_FOUND);
        Proyectos proyectos = servicioProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
}
