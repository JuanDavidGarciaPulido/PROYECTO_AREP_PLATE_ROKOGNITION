package edu.eci.arep.carplaterekognition.controllers;

import com.google.gson.Gson;
import edu.eci.arep.carplaterekognition.models.CarImageProfiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.eci.arep.carplaterekognition.services.RekognitionService;

import java.util.List;

@RestController
@RequestMapping("/check")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CarPlateCheckController {

    @PostMapping()
    public ResponseEntity<?> carPlate(@RequestBody String imageName){
        List<CarImageProfiles> carImageProfiles = RekognitionService.getLabelsfromImage(imageName);
        if (!carImageProfiles.isEmpty()){
            return new ResponseEntity<>(new Gson().toJson(carImageProfiles), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}
