package spring.io;

import java.util.Map;

public class HealthPersonnel extends User{

    public HealthPersonnel() {}

    public HealthPersonnel(String username, String password, String email, String dni, Long id, Map<Integer, String> pacientes , Centro centro) {
        super(username, password, email, dni, id);
    }
}
