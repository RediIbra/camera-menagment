package com.npa.getway.bootstrap;

import com.npa.getway.model.Center;
import com.npa.getway.model.Plate;
import com.npa.getway.model.Users;
import com.npa.getway.model.Webcam;
import com.npa.getway.repositories.CenterRepository;
import com.npa.getway.repositories.PlateRepository;
import com.npa.getway.repositories.UserRepository;
import com.npa.getway.repositories.WebcamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LoadData implements CommandLineRunner {
    private final CenterRepository centerRepository;
    private final PlateRepository plateRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WebcamRepository webcamRepository;


    public LoadData(CenterRepository centerRepository, PlateRepository plateRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, WebcamRepository webcamRepository) {
        this.centerRepository = centerRepository;
        this.plateRepository = plateRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.webcamRepository = webcamRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Plate plate = new Plate();
        plate.setDesc("AA123AB");
        Plate savedPlate = plateRepository.save(plate);

        Center center = new Center();
        center.setDesc("GYM");
        center.setLocation("Tirane, Albania");
        center.setContact("+355699389737");
        center.setCenters_plates(Set.of(plate));
        savedPlate.setCenterSet(Set.of(center));
        Center centerSaved = centerRepository.save(center);
        saveUsers();

        Webcam webcam = new Webcam();
        webcam.setDesc("webcam1");
        webcam.setModel(null);
        webcam.setCenter(centerSaved);
        webcamRepository.save(webcam);

    }


    private void saveUsers(){
        userRepository.save(new Users("admin","admin@mail.com", passwordEncoder.encode("admin"),
                true, true, true, true));
        userRepository.save(  new Users("user", "user@mail.com", passwordEncoder.encode("user"),
                true, true, true, true));
    }
}
