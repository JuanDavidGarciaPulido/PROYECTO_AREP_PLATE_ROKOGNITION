package edu.eci.arep.carplaterekognition.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.util.UUID;

@DynamoDbBean
public class CarImageProfiles {

    private String cartable;
    private String plate;
    private String imageUrl;
    private String description;
    private String date;
    private Boolean isStealed;

    public CarImageProfiles() {
        setCarId(UUID.randomUUID().toString());
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("cartable")
    public String getCarId() {
        return cartable;
    }

    public void setCarId(String carId) {
        this.cartable = carId;
    }

    @DynamoDbAttribute("plate")
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @DynamoDbAttribute("url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @DynamoDbAttribute("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDbAttribute("date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @DynamoDbAttribute("stealed")
    public Boolean getStealed() {
        return isStealed;
    }

    public void setStealed(Boolean stealed) {
        isStealed = stealed;
    }

    @Override
    public String toString() {
        return "CarImageProfiles{" +
                "carId='" + cartable + '\'' +
                ", plate='" + plate + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", isStealed=" + isStealed +
                '}';
    }

}
