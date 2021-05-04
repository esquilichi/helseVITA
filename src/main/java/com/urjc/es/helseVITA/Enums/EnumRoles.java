package com.urjc.es.helseVITA.Enums;

import java.util.List;
import java.util.Random;

public enum EnumRoles {
    CARDIOLOGO, PEDIATRA, DERMATOLOGO, TRAUMATOLOGO, GINECOLOGO, 
    ALERGOLOGO, ENDOCRINO, ENFERMERO, NEUROLOGO, OFTALMOLOGO, 
    ONCOLOGO, PSIQUIATRA, CIRUJANO, ODONTOLOGO, UROLOGO;    

    
    public static final List<EnumRoles> VALUES = List.of(values());
    private static final int SIZE = VALUES.size(); 
    private static final Random RANDOM = new Random(); 
    public static EnumRoles randomRol() { return VALUES.get(RANDOM.nextInt(SIZE)); }


    @Override
    public String toString() {
        return this.name();
    }
}
