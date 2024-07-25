/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainClasses;


import mainClasses.enums.Gender;

import java.time.LocalDate;

/**
 *
 * @author Mike
 */
public class PetOwner {
    int owner_id;
    String username,email,password,firstname,lastname;
    LocalDate birthdate;
    String job;
    Gender gender;
    String country,city,address;
    Double lat,lon;
    String telephone;
    String personalpage;

    public PetOwner(Double lat, Double lon, String username, String email, String password, String firstname, String lastname, LocalDate birthdate, Gender gender, String job, String country, String city, String address, String telephone, String personalpage) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.job = job;
        this.country = country;
        this.city = city;
        this.address = address;
        this.telephone = telephone;
        this.personalpage = personalpage;
        this.lat = lat;
        this.lon = lon;
    }

    public int getOwner_id() {return owner_id;}
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getFirstname() {return firstname;}
    public String getLastname() {return lastname;}
    public LocalDate getBirthdate() {return birthdate;}
    public String getJob() {return job;}
    public Gender getGender() {return gender;}
    public String getCountry() {return country;}
    public String getCity() {return city;}
    public String getAddress() {return address;}
    public Double getLat() {return lat;}
    public Double getLon() {return lon;}
    public String getTelephone() {return telephone;}
    public String getPersonalpage() {return personalpage;}

    @Override
    public String toString() {
        return "PetOwner{" +
                "owner_id=" + owner_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", job='" + job + '\'' +
                ", gender=" + gender +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", telephone='" + telephone + '\'' +
                ", personalpage='" + personalpage + '\'' +
                '}';
    }
}
