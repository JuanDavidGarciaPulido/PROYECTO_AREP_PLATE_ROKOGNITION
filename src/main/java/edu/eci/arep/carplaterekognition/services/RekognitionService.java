package edu.eci.arep.carplaterekognition.services;

import edu.eci.arep.carplaterekognition.constants.CredentialConstants;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

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

    public static Hashtable<String,String> getLabelsfromImage(String image) {
        credentials = AwsSessionCredentials.create(CredentialConstants.AWS_ACCESS_KEY_ID, CredentialConstants.AWS_SECRET_ACCESS_KEY,CredentialConstants.SESSION_TOKEN);
        region = Region.US_EAST_1;
        run();
        Hashtable<String,String> labelMap = new Hashtable<>();
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
                System.out.println("Detected: " + text.detectedText());
                System.out.println("Confidence: " + text.confidence().toString());
                System.out.println("Id : " + text.id());
                System.out.println("Parent Id: " + text.parentId());
                System.out.println("Type: " + text.type());
                System.out.println();
            }

        } catch (RekognitionException e) {
            System.out.println(e.getMessage());
        }
        rekClient.close();
        return labelMap;
    }
}