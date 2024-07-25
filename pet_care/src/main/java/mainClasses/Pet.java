/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

import mainClasses.enums.Gender;
import mainClasses.enums.PetType;

/**
 *
 * @author mountant
 */
public class Pet {
    long pet_id;
    int owner_id;
    String name;
    PetType type;
    String breed;
    Gender gender;
    int birthyear;
    double weight;
    String description;
    String photo;


    public long getPet_id() {return pet_id;}
    public int getOwner_id() {return owner_id;}
    public String getName() {return name;}
    public PetType getType() {return type;}
    public String getBreed() {return breed;}
    public Gender getGender() {return gender;}
    public int getBirthyear() {return birthyear;}
    public double getWeight() {return weight;}
    public String getDescription() {return description;}
    public String getPhoto() {return photo;}

    public void setPet_id(long pet_id) {this.pet_id = pet_id;}
    public void setOwner_id(int owner_id) {this.owner_id = owner_id;}
    public void setName(String name) {this.name = name;}
    public void setType(PetType type) {this.type = type;}
    public void setBreed(String breed) {this.breed = breed;}
    public void setGender(Gender gender) {this.gender = gender;}
    public void setBirthyear(int birthyear) {this.birthyear = birthyear;}
    public void setWeight(double weight) {this.weight = weight;}
    public void setDescription(String description) {this.description = description;}
    public void setPhoto(String photo) {this.photo = photo;}

    @Override
    public String toString() {
        String pic;
        if(photo == null){
            pic = null;
        }else{
            pic = "'" + photo + "'";
        }

        String desc;
        if(description == null){
            desc = null;
        }else{
            desc = "'" + description + "'";
        }

        return "Pet{" +
                "pet_id=" + pet_id +
                ", owner_id=" + owner_id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", breed='" + breed + '\'' +
                ", gender=" + gender +
                ", birthyear=" + birthyear +
                ", weight=" + weight +
                ", description=" + desc +
                ", photo=" + pic +
                '}';
    }

}
