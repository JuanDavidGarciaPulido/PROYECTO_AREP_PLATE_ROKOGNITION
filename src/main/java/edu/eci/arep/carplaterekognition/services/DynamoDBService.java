package edu.eci.arep.carplaterekognition.services;

import edu.eci.arep.carplaterekognition.constants.CredentialConstants;
import edu.eci.arep.carplaterekognition.models.CarImageProfiles;
import edu.eci.arep.carplaterekognition.models.UserProfile;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;

public class DynamoDBService {
    static AwsSessionCredentials credentials;
    static Region region;
    static DynamoDbEnhancedClient enhancedClient;

    DynamoDbTable<CarImageProfiles> carImageProfileTable = null;

    DynamoDbTable<UserProfile> userProfileTable = null;

    DynamoDBService(){
        credentials = AwsSessionCredentials.create(CredentialConstants.AWS_ACCESS_KEY_ID, CredentialConstants.AWS_SECRET_ACCESS_KEY,CredentialConstants.SESSION_TOKEN);
        region = Region.US_EAST_1;
        enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(
                        DynamoDbClient.builder()
                                .region(Region.US_EAST_1)
                                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                                .build())
                .build();
        carImageProfileTable = enhancedClient.table("data_table", TableSchema.fromBean(CarImageProfiles.class));
        userProfileTable = enhancedClient.table("users", TableSchema.fromBean(UserProfile.class));
    }

    public void putCarImageProfile(CarImageProfiles carImageProfile){
        carImageProfileTable.putItem(carImageProfile);
    }

    public List<CarImageProfiles> getCarImageProfiles() {
        return carImageProfileTable.scan().items().stream().toList();
    }

    public void putUserProfile(UserProfile userProfile){
        userProfileTable.putItem(userProfile);
    }

    public UserProfile getUserProfile(String email){
        for (UserProfile userProfile : userProfileTable.scan().items().stream().toList()) {
            if (userProfile.getEmail().equals(email)) {
                return userProfile;
            }
        }
        return null;
    }

    public UserProfile updateUserProfile(UserProfile userProfile){
        return userProfileTable.updateItem(userProfile);
    }
}