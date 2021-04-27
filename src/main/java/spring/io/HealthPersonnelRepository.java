package spring.io;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthPersonnelRepository extends JpaRepository <HealthPersonnel, Integer> {
    public default void updateUsername(String username, Integer id){

    }
    public default void updatePassword(String password, Integer id){

    }
    public default void updateEmail(String email, Integer id){

    }
    public default void updateRole(String role, Integer id){

    }
    public default void updateDni(String dni, Integer id){

    }
    public default void editHealthPersonnel(HealthPersonnel healthPersonnel, Integer id){

    }
    public default HealthPersonnel findByUsername(String username){
        return this.findByUsername(username); //Hay q cambiarlo, está así para q no de error
    }
    public default HealthPersonnel findByEmail(String email){
        return this.findByEmail(email); //Hay q cambiarlo, está así para q no de error
    }
    public default Collection<HealthPersonnel> findByRole(String role){
        return this.findAll(); //Hay q cambiarlo, está así para q no de error
    }
    public default HealthPersonnel findByDni(String dni){
        return this.findByDni(dni); //Hay q cambiarlo, está así para q no de error
    }
}