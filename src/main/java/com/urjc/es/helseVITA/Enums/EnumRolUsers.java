package com.urjc.es.helseVITA.Enums;


//CLARA TE GUSTA COMPLICARTE GRRRRRR VOY A HACERLOS A STRINGS EN EL CONSTRUCTOR, ES UN DOLOR DE CABEZA A ENUM :)
public enum EnumRolUsers {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_PATIENT("ROLE_PATIENT"),
    ROLE_HEALTHPERSONNEL("ROLE_HEALTHPERSONNEL"),
    ROLE_GUEST("ROLE_GUEST");

    private final String rol;

    EnumRolUsers(String str) {
        this.rol = str;
    }


    @Override
    public String toString() {
        return this.rol;
    }
}
