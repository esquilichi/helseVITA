package com.urjc.es.helseVITA.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EnumRoles {
    CARDIOLOGO, PEDIATRA, DERMATOLOGO, TRAUMATOLOGO, GINECOLOGO, 
    ALERGOLOGO, ENDOCRINO, ENFERMERO, NEUROLOGO, OFTALMOLOGO, 
    ONCOLOGO, PSIQUIATRA, CIRUJANO, ODONTOLOGO, UROLOGO;    

    
    private static final List<EnumRoles> VALUES = Collections.unmodifiableList(Arrays.asList(values())); 
    private static final int SIZE = VALUES.size(); 
    private static final Random RANDOM = new Random(); 
    public static EnumRoles randomRol() { return VALUES.get(RANDOM.nextInt(SIZE)); }

}
