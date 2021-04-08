package spring.io;

public class Patient extends User{

    Patient() {}

    public Patient(String username, String password, String correo, String dni, Long id) {
        super(username, password, correo, dni, id);
    }
}
