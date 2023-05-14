
package com.Portfolio.CaluT.Repositorio;

import com.Portfolio.CaluT.Entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositorioPersona extends JpaRepository<Persona,Long> {
    
}
