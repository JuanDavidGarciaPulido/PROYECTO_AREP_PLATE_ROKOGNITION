package edu.eci.arep.carplaterekognition.services;


import edu.eci.arep.carplaterekognition.models.CarImageProfiles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarimageServiceImpl implements CarImageService{
    DynamoDBService dynamoDBService = new DynamoDBService();


    @Override
    public List<CarImageProfiles> getCarImageProfiles() {
        return dynamoDBService.getCarImageProfiles();
    }

    @Override
    public void createCarImageProfile(CarImageProfiles carImageProfile) {
        dynamoDBService.putCarImageProfile(carImageProfile);
    }

    @Override
    public void modifyCarImageProfile(CarImageProfiles carImageProfile) {
    }

    @Override
    public void deleteDogImageProfile(CarImageProfiles carImageProfile) {
    }
}
