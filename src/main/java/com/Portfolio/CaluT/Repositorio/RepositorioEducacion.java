package com.Portfolio.CaluT.Repositorio;

import com.Portfolio.CaluT.Entidades.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEducacion extends JpaRepository<Educacion, Integer> {
    public Optional<Educacion> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);
}
