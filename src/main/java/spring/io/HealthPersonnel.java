package spring.io;

public class HealthPersonnel extends User{

    private String role;

    public HealthPersonnel() {}

    public HealthPersonnel(String username, String password, String email, String dni, Long id, String role) {
        super(username, password, email, dni, id);
        this.role =role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
