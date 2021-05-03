package com.urjc.es.helseVITA.Enums;

import java.util.Map;
import java.util.HashMap;

public enum LetraDni {
    T(0), R(1), W(2), A(3), G(4),  M(5), Y(6), F(7), P(8),	D(9), X(10), B(11), N(12),	J(13), Z(14), S(15), Q(16), V(17), H(18), L(19), C(20), K(21), E(22);

    private int id;
    private static final Map<Integer, LetraDni> MAP = new HashMap<>();

    private LetraDni(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
    public static LetraDni fromId(int id){
        return MAP.get(id);
    }
    static{
        for(LetraDni n : values()){
            MAP.put(n.getId(), n);
        }
    }
}
