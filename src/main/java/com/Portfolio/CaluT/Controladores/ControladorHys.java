package com.Portfolio.CaluT.Controladores;

import com.Portfolio.CaluT.DTO.HySDTO;
import com.Portfolio.CaluT.Entidades.Hys;
import com.Portfolio.CaluT.Seguridad.Controlador.Mensaje;
import com.Portfolio.CaluT.Servicios.ServicioHys;
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
@CrossOrigin(origins = {"https://frontendap-30130.web.app"})
@RequestMapping("/skills")
public class ControladorHys {
    @Autowired
    ServicioHys servicioHys;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list(){
        List<Hys> list = servicioHys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HySDTO hysdto){
        if(StringUtils.isBlank(hysdto.getNombre())){
            return new ResponseEntity(new Mensaje("Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(servicioHys.existsByNombre(hysdto.getNombre())){
            return new ResponseEntity(new Mensaje("Skill ya existente"), HttpStatus.BAD_REQUEST);
        }
        
        Hys hys = new Hys(hysdto.getNombre(), hysdto.getPorcentaje());
        servicioHys.save(hys);
        return new ResponseEntity (new Mensaje("Skill Agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody HySDTO hysdto){
        if(!servicioHys.existsById(id)){
            return new ResponseEntity(new Mensaje ("ID no existente"), HttpStatus.BAD_REQUEST);
        }
        if (servicioHys.existsByNombre(hysdto.getNombre()) && servicioHys.getByNombre(hysdto.getNombre()).get().getId() !=id){
            return new ResponseEntity(new Mensaje ("Skill existente"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(hysdto.getNombre())){
            return new ResponseEntity(new Mensaje ("Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Hys hys = servicioHys.getOne(id).get();
        hys.setNombre(hysdto.getNombre());
        hys.setPorcentaje(hysdto.getPorcentaje());
        
        servicioHys.save(hys);
        return new ResponseEntity (new Mensaje ("Skill Actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!servicioHys.existsById(id)){
            return new ResponseEntity(new Mensaje ("no existente"), HttpStatus.NOT_FOUND);
        }
        servicioHys.delete(id);
        return new ResponseEntity(new Mensaje ("Skill Eliminada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id){
        if(!servicioHys.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Hys hys = servicioHys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
}
