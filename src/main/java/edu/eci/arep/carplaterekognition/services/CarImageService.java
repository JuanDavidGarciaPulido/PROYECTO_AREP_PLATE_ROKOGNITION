package edu.eci.arep.carplaterekognition.services;

import edu.eci.arep.carplaterekognition.models.CarImageProfiles;

import java.util.List;

public interface CarImageService {

    List<CarImageProfiles> getCarImageProfiles();
    void createCarImageProfile(CarImageProfiles carImageProfile);
    void modifyCarImageProfile(CarImageProfiles carImageProfile);
    void deleteDogImageProfile(CarImageProfiles carImageProfile);

}
