package mainClasses.data;

import mainClasses.PetKeeper;
import mainClasses.PetOwner;
import mainClasses.enums.*;
import java.time.LocalDate;

public class SignUpData{

    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private Gender gender;
    private UserType user_type;
    private PetKeeperProperty property;
    private Double dogprice;
    private Double catprice;
    private String country;
    private String city;
    private String address;
    private String propertydescription;
    private String personalpage;
    private String job;
    private String telephone;
    private Hosting hosting;
    private Double lat;
    private Double lon;

    public String getProp_description(){return propertydescription;}
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getFirstname() {return firstname;}
    public String getLastname() {return lastname;}
    public LocalDate getBirthdate() {return birthdate;}
    public Gender getGender() {return gender;}
    public UserType getUser_type() {return user_type;}
    public PetKeeperProperty getProperty() {return property;}
    public Double getDogprice() {return dogprice;}
    public Double getCatprice() {return catprice;}
    public String getCountry() {return country;}
    public String getCity() {return city;}
    public String getAddress() {return address;}
    public String getPersonalpage() {return personalpage;}
    public String getJob() {return job;}
    public String getTelephone() {return telephone;}
    public Hosting getHosting() {return hosting;}
    public Double getLat() {return lat;}
    public Double getLon() {return lon;}

    public PetOwner create_pet_owner_from_sign_up_data(){
        return new PetOwner(this.lat, this.lon,
                this.username, this.email, this.password, this.firstname, this.lastname, this.birthdate, this.gender, this.job, this.country, this.city, this.address, this.telephone, this.personalpage);
    }

    public PetKeeper create_pet_keeper_from_sign_up_data(){
        return new PetKeeper(this.lat, this.lon, this.username, this.email, this.password, this.firstname, this.lastname, this.birthdate, this.gender, this.job, this.country, this.city, this.address, this.telephone, this.personalpage, this.property, this.propertydescription, this.hosting, this.catprice, this.dogprice);
    }

    @Override
    public String toString() {
        return "SignUpData{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", user_type=" + user_type +
                ", property=" + property +
                ", dogprice=" + dogprice +
                ", catprice=" + catprice +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", prop_description='" + propertydescription + '\'' +
                ", personalpage='" + personalpage + '\'' +
                ", job='" + job + '\'' +
                ", telephone='" + telephone + '\'' +
                ", hosting=" + hosting +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}