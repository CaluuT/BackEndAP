package com.Portfolio.CaluT.Repositorio;

import com.Portfolio.CaluT.Entidades.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioExperiencia extends JpaRepository<Experiencia, Integer> {
    public Optional<Experiencia> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);
}
