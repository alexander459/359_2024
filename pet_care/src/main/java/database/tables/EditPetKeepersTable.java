/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mainClasses.PetKeeper;
import database.DB_Connection;
import mainClasses.PetOwner;
import mainClasses.data.SignUpData;
import mainClasses.enums.Gender;
import mainClasses.enums.ResponseValues;
import mainClasses.helpers.LocalDateAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class EditPetKeepersTable {

    private int status;

    public int getStatus(){return this.status;
    }
    public void addPetKeeperFromJSON(String json) throws ClassNotFoundException{
        PetKeeper user=jsonToPetKeeper(json);
        addNewPetKeeper(user);
    }

    public PetKeeper jsonToPetKeeper(String json){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());

        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.fromJson(json, PetKeeper.class);
    }

    public String petKeeperToJSON(PetKeeper user){
        Gson gson = new Gson();

        return gson.toJson(user, PetKeeper.class);
    }


    public void changePassPetKeeper(String username, String pass) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String update="UPDATE petkeepers SET" +
                " password='" + pass + "' " +
                "WHERE username = '" + username +"'";

        stmt.executeUpdate(update);
    }

    public void updatePetKeeper(PetKeeper pk) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE petkeepers SET" +
                " firstname='" + pk.getFirstname() + "', " +
                " lastname='" + pk.getLastname() + "', " +
                " birthdate='" + pk.getBirthdate() + "', " +
                " gender='" + pk.getGender() + "', " +
                " country='" + pk.getCountry() + "', " +
                " city='" + pk.getCity() + "', " +
                " address='" + pk.getAddress() + "', " +
                " personalpage='" + pk.getPersonalpage() + "', " +
                " job='" + pk.getJob() + "', " +
                " telephone='" + pk.getTelephone() + "', " +
                " property='" + pk.getProperty() + "', " +
                " propertydescription='" + pk.getPropertydescription() + "', " +
                " hosting='" + pk.getHosting() + "', " +
                " catprice='" + pk.getCatprice() + "', " +
                " dogprice='" + pk.getDogprice() + "' " +
                "WHERE username = '" + pk.getUsername() +"'";
        stmt.executeUpdate(update);
    }

    public void printPetKeeperDetails(String username, String password) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "' AND password='"+password+"'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public PetKeeper databaseToPetKeepers(String username, String password) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(json, PetKeeper.class);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public PetKeeper databaseToPetKeepers(String username) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(json, PetKeeper.class);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void deletePetKeeper(String username) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "DELETE FROM petkeepers WHERE username = '" + username + "'";
        stmt.execute(query);
        stmt.close();
    }

    public ArrayList<PetKeeper> getAllKeepers() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<PetKeeper> keepers = new ArrayList<PetKeeper>();
        ResultSet rs;

        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);

                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
                builder.setPrettyPrinting();
                Gson gson = builder.create();

                PetKeeper keeper = gson.fromJson(json, PetKeeper.class);
                keepers.add(keeper);
            }
            return keepers;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }



    public ArrayList<PetKeeper> getAvailableKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<PetKeeper> keepers = new ArrayList<PetKeeper>();
        ResultSet rs = null;
        try {
            //if(type=="catkeeper")
            if("all".equals(type))
                rs = stmt.executeQuery("SELECT * FROM `petKeepers` WHERE  `petKeepers`.`keeper_id` not in (select keeper_id "
                        + "from `bookings` where `status`='requested' or  `status`='accepted')\n" +"");
            else if ("catKeepers".equals(type))
                rs = stmt.executeQuery("SELECT * FROM `petKeepers` WHERE `petKeepers`.`catkeeper`='true' AND `petKeepers`.`keeper_id` not in (select keeper_id "
                        + "from `bookings` where `status`='requested' or  `status`='accepted')");
            else if ("dogKeepers".equals(type))
                rs = stmt.executeQuery("SELECT * FROM `petKeepers` WHERE `petKeepers`.`dogkeeper`='true' AND `petKeepers`.`keeper_id` not in (select keeper_id "
                        + "from `bookings` where `status`='requested' or  `status`='accepted')");


            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                PetKeeper keeper = gson.fromJson(json, PetKeeper.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public ArrayList<PetKeeper> getKeepers(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<PetKeeper> keepers = new ArrayList<PetKeeper>();
        ResultSet rs = null;
        try {
            if("catkeeper".equals(type))
                rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE catkeeper= '" + "true" + "'");
            else if ("dogkeeper".equals(type))
                rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE dogkeeper= '" + "true" + "'");


            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                PetKeeper keeper = gson.fromJson(json, PetKeeper.class);
                keepers.add(keeper);
            }
            return keepers;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public String databasePetKeeperToJSON(String username, String password) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            return DB_Connection.getResultsToJSON(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public String databasePetKeeperToJSON(String username) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petkeepers WHERE username = '" + username + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            json = json.substring(0, json.length() - 1);
            json = json + ", \"user_type\":\"PET_KEEPER\"}";
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void createPetKeepersTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE petkeepers "
                + "(keeper_id INTEGER not NULL AUTO_INCREMENT, "
                + "    username VARCHAR(30) not null unique,"
                + "    email VARCHAR(50) not null unique,	"
                + "    password VARCHAR(32) not null,"
                + "    firstname VARCHAR(30) not null,"
                + "    lastname VARCHAR(30) not null,"
                + "    birthdate DATE not null,"
                + "    gender enum('" + Gender.MALE + "', '" + Gender.FEMALE + "') not null,"
                + "    country VARCHAR(30) not null,"
                + "    city VARCHAR(50) not null,"
                + "    address VARCHAR(50) not null,"
                + "    personalpage VARCHAR(200) not null,"
                + "    job VARCHAR(200) not null,"
                + "    telephone VARCHAR(14),"
                + "    lat DOUBLE,"
                + "    lon DOUBLE,"
                + "    property enum('INTERIOR', 'EXTERIOR', 'BOTH') not null,"
                + "    propertydescription VARCHAR(200),"
                + "    hosting enum('DOG_KEEPING', 'CAT_KEEPING', 'BOTH') not null,"
                + "    catprice DOUBLE,"
                + "    dogprice DOUBLE,"
                + " PRIMARY KEY (keeper_id))";
        stmt.execute(query);
        stmt.close();
    }


    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewPetKeeper(PetKeeper user) throws ClassNotFoundException {
        System.out.println(user);
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();




            String insertQuery = "INSERT INTO "
                    + " petkeepers (username,email,password,firstname,lastname,birthdate,gender,country,city,address,personalpage,"
                    + "job,telephone,lat,lon,property,propertydescription,hosting,catprice,dogprice)"
                    + " VALUES ("
                    + "'" + user.getUsername() + "',"
                    + "'" + user.getEmail() + "',"
                    + "'" + user.getPassword() + "',"
                    + "'" + user.getFirstname() + "',"
                    + "'" + user.getLastname() + "',"
                    + "'" + user.getBirthdate() + "',"
                    + "'" + user.getGender() + "',"
                    + "'" + user.getCountry() + "',"
                    + "'" + user.getCity() + "',"
                    + "'" + user.getAddress() + "',"
                    + "'" + user.getPersonalpage() + "',"
                    + "'" + user.getJob() + "',"
                    + "'" + user.getTelephone() + "',"
                    + "'" + user.getLat() + "',"
                    + "'" + user.getLon() + "',"
                    + "'" + user.getProperty() + "',"
                    + "'" + user.getPropertydescription()+ "',"
                    + "'" + user.getHosting() + "',"
                    + "'" + user.getCatprice() + "',"
                    + "'" + user.getDogprice() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The pet keeper was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditPetKeepersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean check_data_valid(PetKeeper user) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM petkeepers WHERE username = '" + user.getUsername() + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            int count = rs.getInt(1);
            assert count == 1 || count == 0;
            if (count == 1) {
                this.status = ResponseValues.USERNAME_EXISTS;
                return false;
            }
        }

        sql = "SELECT COUNT(*) FROM petkeepers WHERE email = '" + user.getEmail() + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            int count = rs.getInt(1);
            assert count == 1 || count == 0;
            if (count == 1) {
                status = ResponseValues.EMAIL_EXISTS;
                return false;
            }
        }

        if(user.getTelephone().isEmpty()) {
            return true;
        }

        sql = "SELECT COUNT(*) FROM petkeepers WHERE telephone = '" + user.getTelephone() + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            int count = rs.getInt(1);
            assert count == 1 || count == 0;
            if (count == 1) {
                status = ResponseValues.PHONE_EXISTS;
                return false;
            }
        }
        stmt.close();
        con.close();
        return true;
    }

    public boolean check_login(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM petkeepers WHERE username = '" + username + "' AND password = '" + password + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            int count = rs.getInt(1);
            assert count == 1 || count == 0;
            if (count == 1) {

                return true;
            }
        }
        status = ResponseValues.WRONG_PASSWORD_OR_USERNAME;
        return false;
    }

}
