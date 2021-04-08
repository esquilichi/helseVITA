package spring.io;

public class Paciente extends User{
    
    private Centro centro;

    Paciente() {}

    public Paciente(String username, String password, String correo, String dni, Long id, Centro centro) {
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
