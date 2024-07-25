package database.DB_main;

import database.DB_Connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Handle {

    public static void create_database() throws SQLException, ClassNotFoundException {
        Connection conn = DB_Connection.getInitialConnection();
        Statement stmt = conn.createStatement();

        String sql_command = "CREATE DATABASE " + DB_Connection.getDatabaseName();
        stmt.execute(sql_command);
        stmt.close();
        conn.close();
    }

    public static void drop_database() throws SQLException, ClassNotFoundException {
        Connection conn = DB_Connection.getInitialConnection();
        Statement stmt = conn.createStatement();
        String sql_command = "DROP DATABASE " + DB_Connection.getDatabaseName();
        stmt.execute(sql_command);
        stmt.close();
        conn.close();
    }

}

