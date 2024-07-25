package database.tables;


import com.google.gson.Gson;
import database.DB_Connection;
import mainClasses.Booking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditBookingsTable {
    public void addBookingFromJSON(String json) throws ClassNotFoundException {
        Booking r = jsonToBooking(json);
        createNewBooking(r);
    }

    public Booking databaseToBooking(int id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM bookings WHERE booking_id= '" + id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            return gson.fromJson(json, Booking.class);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Booking jsonToBooking(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Booking.class);
    }

    public String bookingToJSON(Booking r) {
        Gson gson = new Gson();

        return gson.toJson(r, Booking.class);
    }

    public void updateBooking(int bookingID,  String status) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String updateQuery = "UPDATE bookings SET status='"+status+"' WHERE booking_id= '"+bookingID+"'";
        stmt.executeUpdate(updateQuery);
        stmt.close();
        con.close();
    }

    public void createBookingTable() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE bookings "
                + "(booking_id INTEGER not NULL AUTO_INCREMENT, "
                + " owner_id INTEGER not NULL, "
                + "  pet_id VARCHAR(10) not NULL, "
                + " keeper_id INTEGER not NULL, "
                + " fromdate DATE not NULL, "
                + " todate DATE not NULL, "
                + " status VARCHAR(15) not NULL, "
                + " price INTEGER not NULL, "
                + "FOREIGN KEY (owner_id) REFERENCES petowners(owner_id) ON DELETE CASCADE, "
                + "FOREIGN KEY (pet_id) REFERENCES pets(pet_id) ON DELETE CASCADE, "
                + "FOREIGN KEY (keeper_id) REFERENCES petkeepers(keeper_id) ON DELETE CASCADE, "
                + " PRIMARY KEY (booking_id))";
        stmt.execute(sql);
        stmt.close();
        con.close();

    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewBooking(Booking bor) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " bookings (owner_id,pet_id,keeper_id,fromDate,toDate,status,price)"
                    + " VALUES ("
                    + "'" + bor.getOwner_id() + "',"
                    + "'" + bor.getPet_id() + "',"
                    + "'" + bor.getKeeper_id()+ "',"
                    + "'" + bor.getFromDate() + "',"
                    + "'" + bor.getToDate() + "',"
                    + "'" + bor.getStatus() + "',"
                    + "'" + bor.getPrice() + "'"
                    + ")";
            //stmt.execute(table);

            stmt.executeUpdate(insertQuery);
            System.out.println("# The booking was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditBookingsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}