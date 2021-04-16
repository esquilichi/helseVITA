package spring.io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service

public class HealthPersonnelService {

    private Map <HealthPersonnel, List <Patient>> patientsList = new ConcurrentHashMap< HealthPersonnel, List <Patient>>();
    //private Centro centro;
    //private Map<Long, User> map = new ConcurrentHashMap<Long, User>();
    private Long lastId = (long) -1;

    public HealthPersonnelService(){
        var h = new HealthPersonnel("Dra. Clara","root","imane@helseVita.es","423424E",(long) -1, "Cardióloga");
        addHealthPersonnel(h);
        h = new HealthPersonnel("Dr. Ismael","root","clara@helseVita.es","423424E",(long) -1, "Pediatra");
        addHealthPersonnel(h);
        h = new HealthPersonnel("Dra. Imane","root","denisa@helseVita.es","423424E",(long) -1, "Pediatra");
        addHealthPersonnel(h);
        h = new HealthPersonnel("Dra. Denisa","root","ismael@helseVita.es","423424E",(long) -1, "Cardióloga");
        addHealthPersonnel(h);
    }

    public HealthPersonnel addHealthPersonnel(HealthPersonnel healthPersonnel) {
        lastId++;
        healthPersonnel.setId(lastId);
        List <Patient> patients = new ArrayList<Patient>();
        this.patientsList.put(healthPersonnel, patients);
        return healthPersonnel;
    }

    public boolean exists(Long id) {
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getId()==id)
            return true;
        }
        return false;
    }

    public void editHealthPersonnel(Long id, HealthPersonnel healthPersonnel) {
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setEmail(healthPersonnel.getEmail());
                entry.getKey().setPassword(healthPersonnel.getPassword());
                entry.getKey().setUsername(healthPersonnel.getUsername());
                entry.getKey().setdni(healthPersonnel.getdni());
            }
        }
    }

    public void deleteHealthPersonnel(Long id) {
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getId()==id){
                HealthPersonnel healthPersonnel= (HealthPersonnel) entry.getKey();
                this.patientsList.remove(healthPersonnel);
            }
        }
    }

    public Collection<HealthPersonnel> returnAll() {
        return this.patientsList.keySet();
    }

    public HealthPersonnel search (String username) {
        
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getUsername().equals(username)){
                return entry.getKey();
            }
        }
        return null;
    }

    public HealthPersonnel searchUsername(String username) {
		for(Map.Entry<HealthPersonnel, List <Patient>> entry: patientsList.entrySet()){
			if (entry.getKey().getUsername().equals(username)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public HealthPersonnel searchEmail(String email) {
		for(Map.Entry<HealthPersonnel, List <Patient>> entry: patientsList.entrySet()){
			if (entry.getKey().getEmail().equals(email)) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public HealthPersonnel searchDni(String dni) {
		for(Map.Entry<HealthPersonnel, List <Patient>> entry: patientsList.entrySet()){
			if (entry.getKey().getdni().equals(dni)) {
				return entry.getKey();
			}
		}
		return null;
	}

    public void updateUsername(String username, long id) {
        
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setUsername(username);
                break;
            }
        }
    }

    public void updatePassword(String password, Long id) {
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setPassword(password);
                break;
            }
        }
    }

    public void updateEmail(String email, Long id) {
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setEmail(email);
                break;
            }
        }
    }

    public void updateDNI(String dni, Long id) {
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setdni(dni);
                break;
            }
        }
    }

    public void updateRole(String role, Long id) {
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setRole(role);
                break;
            }
        }
    }

    /*public Centro getCentro() {
        return this.centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }*/

    public List<Patient> getPatients(HealthPersonnel healthPersonnel){
        return this.patientsList.get(healthPersonnel);
    }

    public void setPatient(HealthPersonnel healthPersonnel, Patient patient){
        if(exists(healthPersonnel.getId())){
            this.patientsList.get(healthPersonnel).set(this.patientsList.get(healthPersonnel).size(), patient);
        }

    }

    public HealthPersonnel returnHealthPersonnel(Long id) {
        for (Map.Entry <HealthPersonnel, List<Patient>> entry : patientsList.entrySet()){
            if(entry.getKey().getId()==id){
                return entry.getKey();
            }
        }
        return null;    
    }


}
