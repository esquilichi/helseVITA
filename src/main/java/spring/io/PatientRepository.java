package spring.io;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
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
    public default void editHealthPersonnel(Patient patient, Integer id){

    }
    public default Patient findByUsername(String username){
        return this.findByUsername(username); //Hay q cambiarlo, está así para q no de error
    }
    public default Patient findByEmail(String email){
        return this.findByEmail(email); //Hay q cambiarlo, está así para q no de error
    }
    public default Patient findByDni(String dni){
        return this.findByDni(dni); //Hay q cambiarlo, está así para q no de error
    }
}
