package com.urjc.es.helseVITA.Exceptions;


public class UserAlreadyExistsException extends RuntimeException{

    String username;
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String username) {
        super("El usuario " + username + "ya existe");
        this.username = username;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}





