package database.DB_main;

import java.sql.SQLException;
public class DB_Drop_Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DB_Handle.drop_database();
    }
}
