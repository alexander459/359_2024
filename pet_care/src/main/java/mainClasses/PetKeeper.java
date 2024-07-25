/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

import mainClasses.enums.Gender;
import mainClasses.enums.Hosting;
import mainClasses.enums.PetKeeperProperty;

import java.time.LocalDate;

/**
 *
 * @author mountant
 */
public class PetKeeper {
    private int keeper_id;
    private String username,email,password,firstname,lastname;
    private LocalDate birthdate;
    private String job;
    private Gender gender;
    private String country,city,address;
    private Double lat,lon;
    private String telephone;
    private String personalpage;
    private String propertydescription;
    private PetKeeperProperty property;
    private Hosting hosting;
    private Double catprice, dogprice;

    public PetKeeper(Double lat, Double lon, String username, String email, String password, String firstname, String lastname, LocalDate birthdate, Gender gender, String job, String country, String city, String address, String telephone, String personalpage, PetKeeperProperty property, String propertydescription, Hosting hosting, Double catprice, Double dogprice) {
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
        this.property = property;
        this.propertydescription = propertydescription;
        this.hosting = hosting;
        this.catprice = catprice;
        this.dogprice = dogprice;
        this.lat = lat;
        this.lon = lon;
    }

    public int getKeeper_id() {return keeper_id;}
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
    public String getPropertydescription() {return propertydescription;}
    public PetKeeperProperty getProperty() {return property;}
    public Hosting getHosting() {return hosting;}
    public Double getCatprice() {return catprice;}
    public Double getDogprice() {return dogprice;}

    @Override
    public String toString() {
        return "PetKeeper{" +
                "keeper_id=" + keeper_id +
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
                ", propertydescription='" + propertydescription + '\'' +
                ", property=" + property +
                ", hosting=" + hosting +
                ", catprice=" + catprice +
                ", dogprice=" + dogprice +
                '}';
    }

}
