package com.urjc.es.helseVITA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.HealthPersonnelRepository;
import com.urjc.es.helseVITA.Repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelseVitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelseVitaApplication.class, args);
}

	@Bean
	CommandLineRunner initData(PatientRepository patientRepository, HealthPersonnelRepository healthPersonnelRepository){
		return (args) -> {

		List<String> names = Arrays.asList("Ismael", "Clara", "Imane", "Denisa", "Sara", "Luscas", "Hugo", "Carmen", 
			"Maria", "Paula","Claudia", "Mario", "Diego", "Julia", "Daniel");

		List<String> surnames=Arrays.asList("Gomez", "Contreras", "Garcia", "Lopez", "Martin", "Torres", "Parra", 
			"Flores", "Gonzalez", "Rodriguez", "Perez", "Esquilichi", "Kadiri", "Velasco", "Nevares", "Saez", "Moya",
			"Soler", "Parra", "Martinez");

		Collections.shuffle(names);

		patientRepository.saveAll(IntStream.rangeClosed(1, names.size()).mapToObj((i) -> {
			String name =names.get(i-1);
			String surname1 = surnames.get(ThreadLocalRandom.current().nextInt(surnames.size()));
			String surname2 = surnames.get(ThreadLocalRandom.current().nextInt(surnames.size()));
			return new Patient(String.format("%s%s", name, surname1),String.format("1234%s", name),String.format("%s.%s@helsevita.com",
				name.toLowerCase(),surname1.toLowerCase()),"93827461S", name, surname1, surname2, 14);
		}).collect(Collectors.toList()));

		healthPersonnelRepository.saveAll(IntStream.rangeClosed(1, names.size()).mapToObj((i) -> {
			String name =names.get(i-1);
			String surname1 = surnames.get(ThreadLocalRandom.current().nextInt(surnames.size()));
			String surname2 = surnames.get(ThreadLocalRandom.current().nextInt(surnames.size()));
			return new HealthPersonnel(String.format("%s%s", name, surname1),String.format("1234%s", name),String.format("%s.%s@helsevita.com",
				name.toLowerCase(),surname1.toLowerCase()),"93827461S", fillPatientsList(patientRepository), name, surname1, surname2, 14);
		}).collect(Collectors.toList()));
		};
	}

	private List<Patient> fillPatientsList(PatientRepository patientRepository){
		List <Patient> patientsList=new ArrayList<>();
		while (patientsList.size()<4) {
			int rand; 
			rand = (int) (Math.random() * 10); 
			Optional <Patient> temp = patientRepository.findById(rand);
			if(temp.isPresent()){
				patientsList.add(temp.get());
			}
		}
		return patientsList;
	}

}