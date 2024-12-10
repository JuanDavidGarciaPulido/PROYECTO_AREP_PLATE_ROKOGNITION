package edu.eci.arep.carplaterekognition.services;

import edu.eci.arep.carplaterekognition.models.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    DynamoDBService dynamoDBService = new DynamoDBService();

    @Override
    public UserProfile getUserProfile(String email) {
        return dynamoDBService.getUserProfile(email);
    }

    @Override
    public void createUserProfile(UserProfile userProfile) {
        dynamoDBService.putUserProfile(userProfile);
    }

    @Override
    public UserProfile updateUserProfile(UserProfile userProfile) {
        return dynamoDBService.updateUserProfile(userProfile);
    }
}
