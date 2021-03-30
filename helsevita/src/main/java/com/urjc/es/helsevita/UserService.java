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
}



