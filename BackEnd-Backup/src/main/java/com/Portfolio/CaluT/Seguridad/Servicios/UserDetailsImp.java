package com.Portfolio.CaluT.Seguridad.Servicios;

import com.Portfolio.CaluT.Seguridad.Entity.Usuario;
import com.Portfolio.CaluT.Seguridad.Entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImp implements UserDetailsService {
    @Autowired
    ServicioUsuario servicioUsuario;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = servicioUsuario.getByNombreUsuario(nombreUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }

    public String getNombreUsuarioFromToken(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
