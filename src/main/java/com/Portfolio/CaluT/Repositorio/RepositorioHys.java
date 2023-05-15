package com.Portfolio.CaluT.Repositorio;

import com.Portfolio.CaluT.Entidades.Hys;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioHys extends JpaRepository<Hys, Integer>{
    public Optional<Hys> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
