package com.proyectospring.demo.dao;

import com.proyectospring.demo.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;
    //IMPORTANTE: EN ESTE ARCHIVO SE HACE LA CONEXIÓN CON LA BASE DE DATOS
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
       return entityManager.createQuery(query).getResultList();

    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

        @Override
        public boolean verificarCredenciales(Usuario usuario) {
            String query = "FROM Usuario WHERE email = :email ";
            List<Usuario> lista = entityManager.createQuery(query)
                    .setParameter("email", usuario.getEmail())
                    .getResultList();

            if (lista.isEmpty()) {
                return true;
            }
            String passwordHashed = lista.get(0).getPassword();

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            return argon2.verify(passwordHashed, usuario.getPassword());


        }
}
