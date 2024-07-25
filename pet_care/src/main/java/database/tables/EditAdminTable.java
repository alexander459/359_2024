package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.Admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditAdminTable {

    public Admin databaseToAdmin(String username, String password) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM admin WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);

            Gson gson = new Gson();
            return gson.fromJson(json, Admin.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    public void createAdminTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE admin "
                + "(admin_id INTEGER not NULL AUTO_INCREMENT, "
                + "    username VARCHAR(30) not null unique,"
                + "    password VARCHAR(32) not null,"
                + " PRIMARY KEY (admin_id))";
        stmt.execute(query);
        stmt.close();
    }


    public void changePassAdmin(String pass) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String update="UPDATE admin SET" +
                " password='" + pass + "' " +
                "WHERE username = 'admin'";

        stmt.executeUpdate(update);
    }

    public void addAdminFromJSON(String json) throws ClassNotFoundException{
        Admin admin = jsonToAdmin(json);
        addNewAdmin(admin);
    }

    public Admin jsonToAdmin(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Admin.class);
    }

    public void addNewAdmin(Admin admin) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " admin (username, password)"
                    + " VALUES ("
                    + "'" + admin.getName() + "',"
                    + "'" + admin.getPassword() + "'"
                    + ")";

            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The pet owner was successfully added in the database.");

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditPetOwnersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public String databaseAdminToJSON() throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM admin WHERE username = 'admin'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            json = json.substring(0, json.length() - 1);
            json = json + ", \"user_type\":\"ADMIN\"}";
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

}
