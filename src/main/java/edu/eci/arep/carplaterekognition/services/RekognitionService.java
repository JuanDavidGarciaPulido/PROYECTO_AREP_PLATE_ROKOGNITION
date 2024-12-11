package edu.eci.arep.carplaterekognition.services;

import com.google.gson.Gson;
import edu.eci.arep.carplaterekognition.constants.CredentialConstants;
import edu.eci.arep.carplaterekognition.models.CarImageProfiles;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RekognitionService {
    static AwsSessionCredentials credentials;
    static Region region;
    static RekognitionClient rekClient;

    private static void run(){
        rekClient = RekognitionClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    public static boolean isCarPlate(String reckognizedText){
        return (reckognizedText.length() == 7 || reckognizedText.length() == 6);
    }

    public static List<CarImageProfiles> getLabelsfromImage(String image) {
        credentials = AwsSessionCredentials.create(CredentialConstants.AWS_ACCESS_KEY_ID, CredentialConstants.AWS_SECRET_ACCESS_KEY,CredentialConstants.SESSION_TOKEN);
        region = Region.US_EAST_1;
        run();
       List<CarImageProfiles> carProfilesList = new ArrayList<>();
        try {
            S3Object s3Object = S3Object.builder()
                    .bucket(CredentialConstants.BUCKET)
                    .name(image)
                    .build() ;

            Image myImage = Image.builder()
                    .s3Object(s3Object)
                    .build();

            DetectTextRequest textRequest = DetectTextRequest.builder()
                    .image(myImage)
                    .build();

            DetectTextResponse textResponse = rekClient.detectText(textRequest);
            List<TextDetection> textCollection = textResponse.textDetections();
            System.out.println("Detected lines and words");
            for (TextDetection text: textCollection) {
                List<String> textAndConfidence = new ArrayList<>();
                System.out.println("Detected: " + text.detectedText());
                textAndConfidence.add(text.detectedText());
                System.out.println("Confidence: " + text.confidence().toString());
                textAndConfidence.add(text.confidence().toString());
                System.out.println("Id : " + text.id());
                if (isCarPlate(text.detectedText())) {
                    CarImageProfiles carImageProfiles = new CarImageProfiles();
                    carImageProfiles.setImageUrl(image);
                    carImageProfiles.setPlate(text.detectedText());
                    carImageProfiles.setDate("2024-09-13");
                    carImageProfiles.setDescription("descripcion de prueba");
                    carImageProfiles.setStealed(false);
                    carProfilesList.add(carImageProfiles);
                    System.out.println(new Gson().toJson(carImageProfiles));
                }
                System.out.println("Parent Id: " + text.parentId());
                System.out.println("Type: " + text.type());
                System.out.println();
            }

        } catch (RekognitionException e) {
            System.out.println(e.getMessage());
        }
        rekClient.close();
        return carProfilesList;
    }
}