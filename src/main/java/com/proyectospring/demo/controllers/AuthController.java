package com.proyectospring.demo.controllers;

import com.proyectospring.demo.dao.UsuarioDao;
import com.proyectospring.demo.models.Usuario;
import com.proyectospring.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //para que sepa que es un controlador
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login (@RequestBody Usuario usuario) {

         Usuario usuarioLoggeado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
       if (usuarioLoggeado != null) {
           //throw new RuntimeException("Credenciales inválidas");
              String tokenJWT = jwtUtil.create(String.valueOf(usuarioLoggeado.getId()), usuarioLoggeado.getEmail());

           return tokenJWT;
       }
          return "FAIL";
    }
}
