package com.urjc.es.helsevita;


public class Cliente extends Usuario {
    
    private Centro centro;

    Cliente() {}

    public Cliente(String username, String password, String correo, String dni, Long id, Centro centro) {
        super(username, password, correo, dni, id);
        this.centro = centro;
    }


    public Centro getCentro() {
        return this.centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

}

