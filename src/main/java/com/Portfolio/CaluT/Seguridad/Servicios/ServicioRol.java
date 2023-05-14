package com.Portfolio.CaluT.Seguridad.Servicios;

import com.Portfolio.CaluT.Seguridad.Entity.Rol;
import com.Portfolio.CaluT.Seguridad.Enums.RolNombre;
import com.Portfolio.CaluT.Seguridad.Repositorio.IRepositorioRol;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicioRol {
    @Autowired
    IRepositorioRol iRepositorioRol;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return iRepositorioRol.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        iRepositorioRol.save(rol);
    }
}
