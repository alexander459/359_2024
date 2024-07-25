/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.GsonBuilder;
import mainClasses.PetKeeper;
import mainClasses.PetOwner;
import com.google.gson.Gson;
import database.DB_Connection;
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
public class EditPetOwnersTable {
    private int status;

    public int getStatus(){return this.status;}

    public void addPetOwnerFromJSON(String json) throws ClassNotFoundException{
        PetOwner user=jsonToPetOwner(json);
        addNewPetOwner(user);
    }

    public PetOwner jsonToPetOwner(String json){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());

        builder.setPrettyPrinting();

        Gson gson = builder.create();
        return gson.fromJson(json, PetOwner.class);
    }

    public String petOwnerToJSON(PetOwner user){
        Gson gson = new Gson();

        return gson.toJson(user, PetOwner.class);
    }



    public void updatePetOwner(PetOwner po) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String update="UPDATE petowners SET" +
                " firstname='" + po.getFirstname() + "', " +
                " lastname='" + po.getLastname() + "', " +
                " birthdate='" + po.getBirthdate() + "', " +
                " gender='" + po.getGender() + "', " +
                " country='" + po.getCountry() + "', " +
                " city='" + po.getCity() + "', " +
                " address='" + po.getAddress() + "', " +
                " personalpage='" + po.getPersonalpage() + "', " +
                " job='" + po.getJob() + "', " +
                " telephone='" + po.getTelephone() + "' " +
                "WHERE username = '" + po.getUsername() +"'";
        
        stmt.executeUpdate(update);
    }


    public PetOwner databaseToPetOwners(String username, String password) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petowners WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(json, PetOwner.class);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public PetOwner databaseToPetOwners(String username) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petowners WHERE username = '" + username + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(json, PetOwner.class);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String databasePetOwnerToJSON(String username, String password) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petowners WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            return DB_Connection.getResultsToJSON(rs);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String databasePetOwnerToJSON(String username) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM petowners WHERE username = '" + username + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            json = json.substring(0, json.length() - 1);
            json = json + ", \"user_type\":\"PET_OWNER\"}";
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void deletePetOwner(String username) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "DELETE FROM petowners WHERE username = '" + username + "'";
        stmt.execute(query);
        stmt.close();
    }

    public void createPetOwnersTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE petowners "
                + "(owner_id INTEGER not NULL AUTO_INCREMENT, "
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
                + " PRIMARY KEY (owner_id))";
        stmt.execute(query);
        stmt.close();
    }


    public ArrayList<PetOwner> getAllOwners() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<PetOwner> owners = new ArrayList<PetOwner>();
        ResultSet rs;

        try {
            rs = stmt.executeQuery("SELECT * FROM petowners");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);

                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
                builder.setPrettyPrinting();
                Gson gson = builder.create();

                PetOwner owner = gson.fromJson(json, PetOwner.class);
                owners.add(owner);
            }
            return owners;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }



    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewPetOwner(PetOwner user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " petowners (username,email,password,firstname,lastname,birthdate,gender,country,city,address,personalpage,"
                    + "job,telephone,lat,lon)"
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
                    + "'" + user.getLon() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The pet owner was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditPetOwnersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePassPetOwner(String username, String pass) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String update="UPDATE petowners SET" +
                " password='" + pass + "' " +
                "WHERE username = '" + username +"'";

        stmt.executeUpdate(update);
    }

    public boolean check_data_valid(PetOwner user) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM petowners WHERE username = '" + user.getUsername() + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            int count = rs.getInt(1);
            assert count == 1 || count == 0;
            if (count == 1) {
                this.status = ResponseValues.USERNAME_EXISTS;
                return false;
            }
        }

        sql = "SELECT COUNT(*) FROM petowners WHERE email = '" + user.getEmail() + "'";
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

        sql = "SELECT COUNT(*) FROM petowners WHERE telephone = '" + user.getTelephone() + "'";
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

        sql = "SELECT COUNT(*) FROM petowners WHERE username = '" + username + "' AND password = '" + password + "'";
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
