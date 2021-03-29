package com.urjc.es.helsevita;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private Map <Long,Usuario> mapa = new ConcurrentHashMap<Long,Usuario>();
    private Long lastId = (long) -1;

    public Usuario agregarUsuario(Usuario user){
        lastId++;
        user.setId(lastId);
        this.mapa.put(lastId, user);
        return user;
    }

    public boolean existe(Long id) {
        return this.mapa.get(id) != null;
    }

    
    public void editarUsuario(Long id, Usuario usuario){
        if (this.mapa.get(id) != null){
            usuario.setId(id);
            this.mapa.put(id, usuario);
        }  
    }

    public void eliminarUsuario(Long id){
        this.mapa.remove(id);
    }

    
    public Usuario devolverUsuario(Long id){
        return this.mapa.get(id);
    }

    public Collection<Usuario> devolverTodos(){
        return this.mapa.values();
    }
    
    //FUNCIONA JEJEJEJEJEJEJEJEJEJE
    public Usuario buscar(String username){
        
        Iterator<Entry<Long, Usuario>> iterator = mapa.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Long, Usuario> entry = iterator.next();
            //si tocais el .equals os parto un brazo <3
            if (entry.getValue().getUsername().equals(username)){
                System.out.println(entry.getValue().getUsername());
                return entry.getValue();
            }
        }
        return null;
    }
}



