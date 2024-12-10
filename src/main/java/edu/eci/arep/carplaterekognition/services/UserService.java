package edu.eci.arep.carplaterekognition.services;

import edu.eci.arep.carplaterekognition.models.UserProfile;

public interface UserService {

    UserProfile getUserProfile(String email);
    void createUserProfile(UserProfile userProfile);
    UserProfile updateUserProfile(UserProfile userProfile);

}
