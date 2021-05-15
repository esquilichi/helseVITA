package com.urjc.es.helseVITA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.urjc.es.helseVITA.Entities.HealthPersonnel;
import com.urjc.es.helseVITA.Entities.Patient;
import com.urjc.es.helseVITA.Repositories.HealthPersonnelRepository;
import com.urjc.es.helseVITA.Repositories.PatientRepository;
import com.urjc.es.helseVITA.Enums.EnumRoles;
import com.urjc.es.helseVITA.Enums.LetraDni;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HelseVitaApplication {


    public static void main(String[] args) {
        SpringApplication.run(HelseVitaApplication.class, args);
    }

}
   /* @Bean
    CommandLineRunner initData(PatientRepository patientRepository, HealthPersonnelRepository healthPersonnelRepository) {
        return (args) -> {
            if (patientRepository.findAll().size() < 2) {
                patientRepository.saveAndFlush(new Patient("IsmaelEsquilichi", new BCryptPasswordEncoder().encode("root"), "ismael.esquilichi@helsevita.com", "4820096E", "Ismael", "Gómez", "Esquilichi", 20));
                healthPersonnelRepository.saveAndFlush(new HealthPersonnel("ClaraContreras", new BCryptPasswordEncoder().encode("root"), "clara.contreras@helsevita.com", "7563289Y", "Clara", "Contreras", "Nevares", 19, "Cardióloga"));
                patientRepository.saveAndFlush(new Patient("ImaneKadiri", new BCryptPasswordEncoder().encode("root"), "imane.kadiri@helsevita.com", "4820096E", "Imane", "Kadiri", "Yamani", 21));
                healthPersonnelRepository.saveAndFlush(new HealthPersonnel("Denisa", new BCryptPasswordEncoder().encode("root"), "denisa.noloquieroponermal@helsevita.com", "7563289Y", "Denisa", "Maria", "Medovarschi", 24, "Cardióloga"));
            }


            List<String> patientNames = Arrays.asList("Ismael", "Clara", "Imane", "Denisa", "Sara", "Luscas", "Hugo", "Carmen",
                    "María", "Paula", "Claudia", "Mario", "Diego", "Julia", "Daniel");

            List<String> healthPersonnelNames = Arrays.asList("Carlos", "Elena", "Alba", "Sandra", "Iván", "Alejandro", "Rodrigo", "Rafael",
                    "Yolanda", "Marina", "Sofía", "Esperanza", "Rosa", "Bárbara", "Azucena");

            List<String> surnames = Arrays.asList("Gómez", "Contreras", "García", "López", "Martín", "Torres", "Parra",
                    "Flores", "González", "Rodríguez", "Perez", "Esquilichi", "Kadiri", "Velasco", "Nevares", "Saez", "Moya",
                    "Soler", "Parra", "Martínez");

            Collections.shuffle(patientNames);
            Collections.shuffle(healthPersonnelNames);

            patientRepository.saveAll(IntStream.rangeClosed(1, patientNames.size()).mapToObj((i) -> {
                String name = patientNames.get(i - 1);
                String surname1 = surnames.get(ThreadLocalRandom.current().nextInt(surnames.size()));
                String surname2 = surnames.get(ThreadLocalRandom.current().nextInt(surnames.size()));
                List<HealthPersonnel> healthPersonnelList = fillHealthPersonnelList(healthPersonnelRepository);
                String dni = newDni();


                Patient temp = new Patient(String.format("%s%s", name, surname1), new BCryptPasswordEncoder().encode(String.format("1234%s", name)), String.format("%s.%s@helsevita.com",
                        name.toLowerCase(), surname1.toLowerCase()), dni, name, surname1, surname2, (int) (Math.random() * 45 + 22));
                temp.setHealthPersonnelList(healthPersonnelList);
                return temp;
            }).collect(Collectors.toList()));

            healthPersonnelRepository.saveAll(IntStream.rangeClosed(1, healthPersonnelNames.size()).mapToObj((i) -> {
                String name = healthPersonnelNames.get(i - 1);
                String surname1 = surnames.get(ThreadLocalRandom.current().nextInt(surnames.size()));
                String surname2 = surnames.get(ThreadLocalRandom.current().nextInt(surnames.size()));
                List<Patient> patientsList = fillPatientsList(patientRepository);
                String dni = newDni();

                HealthPersonnel temp = new HealthPersonnel(String.format("%s%s", name, surname1), new BCryptPasswordEncoder().encode(String.format("1234%s", name)), String.format("%s.%s@helsevita.com",
                        name.toLowerCase(), surname1.toLowerCase()), dni, name, surname1, surname2, (int) (Math.random() * 95), EnumRoles.randomRol().toString());
                temp.setPatients(patientsList);
                return temp;
            }).collect(Collectors.toList()));
        };
    }

    private List<Patient> fillPatientsList(PatientRepository patientRepository) {
        List<Patient> patientsList = new ArrayList<>();
        while (patientsList.size() < 4) {
            int rand;
            rand = (int) (Math.random() * patientRepository.findAll().size());
            Optional<Patient> temp = patientRepository.findById(rand);
            temp.ifPresent(patientsList::add);
        }
        return patientsList;
    }

    private List<HealthPersonnel> fillHealthPersonnelList(HealthPersonnelRepository healthPersonnelRepository) {
        List<HealthPersonnel> healthPersonnelList = new ArrayList<>();
        while (healthPersonnelList.size() < 4) {
            int rand;
            rand = (int) (Math.random() * healthPersonnelRepository.findAll().size());
            Optional<HealthPersonnel> temp = healthPersonnelRepository.findById(rand);
            temp.ifPresent(healthPersonnelList::add);
        }
        return healthPersonnelList;
    }

    private String newDni() {
        int dni[] = new int[8];
        Random r = new Random();
        int total = 0;
        String dniFinal = "";

        for (int i = 0; i < 8; i++) {
            dni[i] = r.nextInt(10);
        }
        for (int i = 0; i < 8; i++) {
            total += dni[i];
        }
        for (int i = 0; i < 8; i++) {
            dniFinal = (dniFinal + String.valueOf(dni[i]));
        }
        total = total % 23;

        dniFinal = (dniFinal + LetraDni.fromId(total));
        return dniFinal;
    }

}*/