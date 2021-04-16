package spring.io;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private Map <Patient, HealthPersonnel> map = new ConcurrentHashMap< Patient, HealthPersonnel>();
    //private Centro centro;
    //private Map<Long, User> map = new ConcurrentHashMap<Long, User>();
    private Long lastId = (long) -1;

    public Patient addPatient(Patient patient, HealthPersonnel healthPersonnel) {
        lastId++;
        patient.setId(lastId);
        this.map.put(patient, healthPersonnel);
        return patient;
    }

    public boolean exists(Long id) {
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id)
            return true;
        }
        return false;
    }

    public void editPatient(Long id, Patient patient) {
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setEmail(patient.getEmail());
                entry.getKey().setPassword(patient.getPassword());
                entry.getKey().setUsername(patient.getUsername());
                entry.getKey().setdni(patient.getdni());
            }
        }
        /*if(this.exists(id)){
             var temp = this.map.get(patient);
            this.map.remove(patient);
            this.map.put(patient, temp);

        }*/
       
    }




    public void deletePatient(Long id) {
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                Patient patient= (Patient) entry.getKey();
                this.map.remove(patient);
            }
        }
    }

    public Collection<Patient> returnAll() {
        return this.map.keySet();
    }

    public Patient search (String username) {
        
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getUsername().equals(username)){
                return entry.getKey();
            }
        }
        return null;
    }

    public void updateUsername(String username, long id) {
        
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setUsername(username);
                break;
            }
        }
    }

    public void updatePassword(String password, Long id) {
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setPassword(password);
                break;
            }
        }
    }

    public void updateEmail(String email, Long id) {
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setEmail(email);
                break;
            }
        }
    }

    public void updateDNI(String dni, Long id) {
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().setdni(dni);
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

    public HealthPersonnel getHealthPersonnel(Patient patient){
        return this.map.get(patient);
    }

    public void updateHealthPersonnel(HealthPersonnel healthPersonnel, Patient patient){
        if(exists(healthPersonnel.getId())){
            for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
                if(entry.getKey().equals(patient)){
                    this.map.replace(patient, healthPersonnel);
                    break;
                }
            }
        }
    }

    public Patient returnPatient(Long id) {
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                return entry.getKey();
            }
        }
        return null;
    }

    public Patient searchUsername(String username) {
		for(Map.Entry<Patient, HealthPersonnel> entry: map.entrySet()){
			if (entry.getKey().getUsername().equals(username)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public Patient searchEmail(String email) {
		for(Map.Entry<Patient, HealthPersonnel> entry: map.entrySet()){
			if (entry.getKey().getEmail().equals(email)) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	public Patient searchDni(String dni) {
		for(Map.Entry<Patient, HealthPersonnel> entry: map.entrySet()){
			if (entry.getKey().getdni().equals(dni)) {
				return entry.getKey();
			}
		}
		return null;
	}

    public List<Appointment> returnAllAppointments(long id){
        Patient temp = null;
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                temp = entry.getKey();
                break;
            }
        }

        if (temp != null){
            return temp.returnAllAppoinments();
        } else {
            return null;
        }
    }


    public void addAppointment(Appointment appointment, Long id){
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                entry.getKey().addAppointment(appointment.getHour(), appointment.getDay(), appointment.getMonth(), appointment.getYear());
                break;
            }
        }
    }

    public void deleteAppointment(Long id, Long id_appointment) {
        for (Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                for(Appointment a : entry.getKey().appointments){
                    if(a.getId()==id_appointment){
                        entry.getKey().appointments.remove(a);
                    }
                }
            }
        }
    }

    public boolean appointmentExists(Long id, Long id_appointment){
        for(Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                for(Appointment a : entry.getKey().appointments){
                    if(a.getId()==id_appointment){
                        return true;
                    }
                } 
            }        
        }
        return false;
    }

    public Appointment returnAppointment(Long id, Long id_appointment){
        for(Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                for(Appointment a : entry.getKey().appointments){
                    if(a.getId()==id_appointment){
                        return a;
                    }
                } 
            }        
        }
        return null;
    }
    public void editAppointment(Long id, Appointment appointment, Long id_appointment){
        for(Map.Entry <Patient, HealthPersonnel> entry : map.entrySet()){
            if(entry.getKey().getId()==id){
                for(Appointment a : entry.getKey().appointments){
                    if(a.getId()==id_appointment){
                        a.setHour(appointment.getHour());
			a.setDay(appointment.getDay());
                        a.setMonth(appointment.getMonth());
                        a.setYear(appointment.getYear());
                    }
                } 
            }        
        }
    }

    

}
