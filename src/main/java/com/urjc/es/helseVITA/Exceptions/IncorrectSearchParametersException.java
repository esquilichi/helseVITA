package com.urjc.es.helseVITA.Exceptions;

public class IncorrectSearchParametersException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IncorrectSearchParametersException(){
        super("Debes seleccionar un campo de búsqueda.");

    }
}

