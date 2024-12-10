package edu.eci.arep.carplaterekognition.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.util.UUID;

@DynamoDbBean
public class UserProfile {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String imageUrl;
    private String plates;

    public UserProfile() {setId(UUID.randomUUID().toString());}

    @DynamoDbPartitionKey
    @DynamoDbAttribute("carusers")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDbAttribute("phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDbAttribute("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDbAttribute("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @DynamoDbAttribute("plates")
    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", plates='" + plates + '\'' +
                '}';
    }
}
