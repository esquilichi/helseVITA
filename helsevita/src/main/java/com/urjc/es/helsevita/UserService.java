package com.urjc.es.helsevita;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repositorio;
    private Long lastId = (long) -1;

    public Usuario agregarUsuario(Usuario user){
        lastId++;
        return repositorio.agregarUsuario(user, lastId);
    }

    public boolean existe(Long id) {
        return repositorio.existe(id);
    }

    
    public void editarUsuario(Long id, Usuario usuario){
        if (repositorio.existe(id)){
            repositorio.editarUsuario(id, usuario);
        }       
    }

    public void eliminarUsuario(Long id){
        repositorio.eliminarUsuario(id);
    }

    
    public Usuario devolverUsuario(Long id){
        return repositorio.devolverUsuario(id);
    }

    public Collection<Usuario> devolverTodos(){
        return repositorio.devolverTodos();
    }
    
    public Usuario buscar(String username){
        return repositorio.buscar(username);
    }

    public void actualizarUsuario(String usuario, long id){
        repositorio.actualizarUsuario(usuario, id);
    }


    public void actualizarPassword(String password, Long id){
        repositorio.actualizarPassword(password, id);
    }

    public void actualizarCorreo(String correo, Long id){
        repositorio.actualizarCorreo(correo,id);
    }

    public void actualizarDNI(String dni, Long id){
        repositorio.actualizarDNI(dni, id);
    }
}



