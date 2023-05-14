package com.Portfolio.CaluT.Seguridad.Servicios;

import com.Portfolio.CaluT.Seguridad.Entity.Usuario;
import com.Portfolio.CaluT.Seguridad.Repositorio.IRepositorioUsuario;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicioUsuario {

    @Autowired
    IRepositorioUsuario irepositorioUsuario;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return irepositorioUsuario.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario) {
        return irepositorioUsuario.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email) {
        return irepositorioUsuario.existsByEmail(email);
    }
    
    public void save (Usuario usuario){
        irepositorioUsuario.save(usuario);
        }
    }
