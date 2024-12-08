package edu.eci.arep.carplaterekognition.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.eci.arep.carplaterekognition.services.RekognitionService;

@RestController
@RequestMapping("/check")
@CrossOrigin(maxAge = 3600)
public class CarPlateCheckController {

    @PostMapping()
    public ResponseEntity<?> carPlate(@RequestBody String imageName){
        if (!RekognitionService.getLabelsfromImage(imageName).keySet().isEmpty()){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}
