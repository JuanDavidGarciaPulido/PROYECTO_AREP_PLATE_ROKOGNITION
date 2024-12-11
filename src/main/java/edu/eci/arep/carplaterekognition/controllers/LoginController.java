package edu.eci.arep.carplaterekognition.controllers;

import edu.eci.arep.carplaterekognition.models.CarImageProfiles;
import edu.eci.arep.carplaterekognition.models.UserProfile;
import edu.eci.arep.carplaterekognition.services.UserService;
import edu.eci.arep.carplaterekognition.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*",maxAge = 3600)
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<Object> login(@RequestBody UserProfile userProfile) {
        String email = userProfile.getEmail();
        if (userService.getUserProfile(email) != null) {
            if (Objects.equals(userService.getUserProfile(email).getPassword(), userProfile.getPassword())) {
                return new ResponseEntity<>(userService.getUserProfile(email), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<Object> edit(@RequestBody UserProfile userProfile) {
        String email = userProfile.getEmail();
        if (userService.getUserProfile(email) == null) {
            return new ResponseEntity<>("User doesnt exists", HttpStatus.CONFLICT);
        } else {
            userService.updateUserProfile(userProfile);
            return new ResponseEntity<>("User updated successfully", HttpStatus.CREATED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody UserProfile userProfile) {
        String email = userProfile.getEmail();
        if (userService.getUserProfile(email) != null) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } else {
            userService.createUserProfile(userProfile);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        }
    }


}
