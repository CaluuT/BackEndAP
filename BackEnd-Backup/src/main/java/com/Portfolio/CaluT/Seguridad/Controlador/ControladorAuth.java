package com.Portfolio.CaluT.Seguridad.Controlador;

import com.Portfolio.CaluT.Seguridad.DTO.JwtDto;
import com.Portfolio.CaluT.Seguridad.DTO.LoginUsuario;
import com.Portfolio.CaluT.Seguridad.DTO.NuevoUsuario;
import com.Portfolio.CaluT.Seguridad.Entity.Rol;
import com.Portfolio.CaluT.Seguridad.Entity.Usuario;
import com.Portfolio.CaluT.Seguridad.Enums.RolNombre;
import com.Portfolio.CaluT.Seguridad.JWT.JwtProvider;
import com.Portfolio.CaluT.Seguridad.Servicios.ServicioRol;
import com.Portfolio.CaluT.Seguridad.Servicios.ServicioUsuario;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class ControladorAuth {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ServicioUsuario servicioUsuario;
    @Autowired
    ServicioRol servicioRol;
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos Erroneos"),HttpStatus.BAD_REQUEST);
        
        if(servicioUsuario.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Nombre de usuario existente"),HttpStatus.BAD_REQUEST);
                    
        if(servicioUsuario.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Email existente"),HttpStatus.BAD_REQUEST);
                    
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(servicioRol.getByRolNombre(RolNombre.ROLE_USER).get());
        
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(servicioRol.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        servicioUsuario.save(usuario);
        
        return new ResponseEntity(new Mensaje ("Usuario guardado"),HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos Erroneos"),HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.gerateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
