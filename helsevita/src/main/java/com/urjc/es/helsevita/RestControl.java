package com.urjc.es.helsevita;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControl {
     
    Set <Integer> conjunto = new TreeSet<>();
    @Autowired
    UserService gestor;

    @PostMapping("/api")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario nuevoUsuario(@RequestBody Usuario usuario) {
        return gestor.agregarUsuario(usuario);
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {

        if  (gestor.existe(id)){
            gestor.editarUsuario(id,usuario);
            return new ResponseEntity<>(usuario,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){

        if(gestor.existe(id)){
            gestor.eliminarUsuario(id);

            return new ResponseEntity<>(usuario,HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/{id}")
	public ResponseEntity<Usuario> obtenerusuario(@PathVariable Long id) {
		if (gestor.existe(id)) {
            Usuario usuarioTemp = gestor.devolverUsuario(id);
			return new ResponseEntity<>(usuarioTemp, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @GetMapping("/api")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Usuario> anuncios() {
		return gestor.devolverTodos();
	}

    @PatchMapping("/api/{id}")
    public ResponseEntity<Usuario> patch(@RequestBody Usuario usuario, @PathVariable Long id){
        if (gestor.existe(id)){
            if(usuario.getUsername() != null)
                gestor.actualizarUsuario(usuario.getUsername(), id);
            if(usuario.getPassword() != null)
                gestor.actualizarPassword(usuario.getPassword(),id);
            if(usuario.getCorreo() != null)
                gestor.actualizarCorreo(usuario.getCorreo(),id);
            if(usuario.getdni() != null)
                gestor.actualizarDNI(usuario.getdni(),id);

            Usuario usuarioTemp = gestor.devolverUsuario(id);
                
            return new ResponseEntity<>(usuarioTemp, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
