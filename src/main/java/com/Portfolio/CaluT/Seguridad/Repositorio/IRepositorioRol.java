
package com.Portfolio.CaluT.Seguridad.Repositorio;

import com.Portfolio.CaluT.Seguridad.Entity.Rol;
import com.Portfolio.CaluT.Seguridad.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioRol extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
