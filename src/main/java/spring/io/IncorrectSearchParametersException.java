package spring.io;

public class IncorrectSearchParametersException extends RuntimeException{ 

    private static final long serialVersionUID = 1L;
    
    IncorrectSearchParametersException(){
        super("Debes seleccionar un campo de búsqueda.");
        
    }
}
