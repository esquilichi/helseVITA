package spring.io;


//No funcionan

public class UserNotFoundException extends RuntimeException{ 

    String username;
    private static final long serialVersionUID = 1L;
    
    UserNotFoundException(String username){
        super("No se ha encontrado el usuario: ");
        this.username = username;
    }



    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
