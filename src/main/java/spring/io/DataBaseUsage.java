package spring.io;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseUsage implements CommandLineRunner {
    @Autowired
    private HealthPersonnelRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new HealthPersonnel("Jack", "1234", "", null, null, null));
        repository.save(new HealthPersonnel("Chloe", "O'Brian", null, null, null, null));
        List<HealthPersonnel> healthPersonnels = repository.findAll();
        for (HealthPersonnel healthPersonnelList : healthPersonnels) {
            System.out.println(healthPersonnelList);
    }

    repository.delete(healthPersonnels.get(0));
    }
}
