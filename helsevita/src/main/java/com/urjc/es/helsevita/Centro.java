package com.urjc.es.helsevita;

public class Centro {
    private String nombre;
    private String direccion;
    private Long codigo_centro;



    public Centro() {
    }


    public Centro(String nombre, String direccion, Long codigo_centro) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigo_centro = codigo_centro;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getCodigo_centro() {
        return this.codigo_centro;
    }

    public void setCodigo_centro(Long codigo_centro) {
        this.codigo_centro = codigo_centro;
    }

}
