package com.proyectospring.demo.controllers;

import com.proyectospring.demo.dao.UsuarioDao;
import com.proyectospring.demo.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //para que sepa que es un controlador
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login (@RequestBody Usuario usuario) {

       if (usuarioDao.verificarCredenciales(usuario)) {
           //throw new RuntimeException("Credenciales inválidas");
           //creación de token

           return "OK";
       }
          return "FAIL";
    }
}
