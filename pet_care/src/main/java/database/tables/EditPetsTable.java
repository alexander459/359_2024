/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import mainClasses.Pet;
import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.data.IdOwnerData;
import mainClasses.enums.BreedType;
import mainClasses.enums.Gender;
import mainClasses.enums.PetType;
import mainClasses.enums.ResponseValues;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class EditPetsTable {

    public void addPetFromJSON(String json) throws ClassNotFoundException {
        Pet bt = jsonToPet(json);
        createNewPet(bt);
    }

    public Pet jsonToPet(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Pet.class);
    }

    public String petToJSON(Pet bt) {
        Gson gson = new Gson();

        return gson.toJson(bt, Pet.class);
    }

    public ArrayList<Pet> databaseToPets() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Pet> pets = new ArrayList<Pet>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM pets");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Pet pet = gson.fromJson(json, Pet.class);
                pets.add(pet);
            }
            return pets;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public Pet petOfOwner(String id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        Pet pet = new Pet();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM pets WHERE owner_id= '" + id + "'");

            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                pet = gson.fromJson(json, Pet.class);

            }
            return pet;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Pet> databaseToPets(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Pet> pets = new ArrayList<Pet>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM pets WHERE type= '" + type + "'");

            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Pet pet = gson.fromJson(json, Pet.class);
                pets.add(pet);
            }
            return pets;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updatePet(String id, String name) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        Pet bt = new Pet();

        String update = "UPDATE pets SET name='" + name + "'" + "WHERE pet_id = '" + id + "'";
        //stmt.executeUpdate(update);
    }

    public void deletePet(String id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM pets WHERE pet_id='" + id + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }

    public void createPetsTable() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE pets "
                + "(pet_id VARCHAR(10) not NULL unique, "
                + "owner_id INTEGER not null,"
                + "name VARCHAR(30) not null,"
                + "type enum('" + PetType.DOG + "', '" + PetType.CAT + "') not null, "
                + "breed enum('" + BreedType.AEGEAN + "', '" + BreedType.LABRADOR + "', '" + BreedType.TURKISH + "') not null, "
                + "gender enum('" + Gender.MALE + "', '" + Gender.FEMALE + "') not null, "
                + "birthyear INTEGER not null , "
                + "weight DOUBLE not null , "
                + "description VARCHAR (500), "
                + "photo VARCHAR (300), "
                + "PRIMARY KEY (pet_id ), "
                + "FOREIGN KEY (owner_id) REFERENCES petowners(owner_id) ON DELETE CASCADE)";
        stmt.execute(sql);
        stmt.close();
        con.close();

    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewPet(Pet bt) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " pets (pet_id,owner_id,name,type,breed,gender,birthyear,weight,description,photo) "
                    + " VALUES ("
                    + "'" + bt.getPet_id() + "',"
                    + "'" + bt.getOwner_id() + "',"
                    + "'" + bt.getName() + "',"
                    + "'" + bt.getType()+ "',"
                    + "'" + bt.getBreed()+ "',"
                    + "'" + bt.getGender()+ "',"
                    + "'" + bt.getBirthyear()+ "',"
                    + "'" + bt.getWeight() + "',"
                    + "'" + bt.getDescription() + "',"
                    + "'" + bt.getPhoto() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The pet was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditPetsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public Pet get_pet(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        String sql;
        String json;
        int id;

        sql = "SELECT owner_id FROM petowners WHERE username = '" + username + "'";
        rs = stmt.executeQuery(sql);
        if(rs.next()) {
            json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            IdOwnerData id_data = gson.fromJson(json, IdOwnerData.class);
            id = id_data.getOwner_id();
        }else{
            return null;
        }

        sql = "SELECT * FROM pets WHERE owner_id = '" + id + "'";
        rs = stmt.executeQuery(sql);
        if(rs.next()){
            json = DB_Connection.getResultsToJSON(rs);
        }else {
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(json, Pet.class);
    }

    public boolean check_pet_id_valid(long id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        String sql;

        sql = "SELECT COUNT(*) FROM pets WHERE pet_id = '" + id + "'";
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            int count = rs.getInt(1);
            assert count == 1 || count == 0;
            if (count == 1) {
                return false;
            }
        }
        return true;
    }
}
