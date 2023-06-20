package com.Portfolio.CaluT.Repositorio;

import com.Portfolio.CaluT.Entidades.Proyectos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProyectos extends JpaRepository<Proyectos, Integer>{
    public Optional<Proyectos> findByNombreP(String nombreP);
    public boolean existsByNombreP(String nombreP);
}
