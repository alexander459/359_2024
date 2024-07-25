package database.DB_main;

import mainClasses.enums.UserType;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DB_Drop_Main.main(args);
        DB_Create_Main.main(args);

        //DB_Handle.create_database("alex");
        //DB_Connector db = new DB_Connector("alex");
       // Connection con = db.getConnection();
       // Statement stmt = con.createStatement();
       // String sql;

        //UserTable.create_table("alex");
        //User u = new User("nikddos", "kilis", "al@al", "565464", UserType.PET_OWNER, "alex");
        //UserTable.add_entry("alex", u);
/*
        sql = "CREATE TABLE user "
                + "(id INTEGER AUTO_INCREMENT,"
                + "username VARCHAR (30), "
                + "password VARCHAR (30), "
                + "PRIMARY KEY (id))";
        stmt.execute(sql);

        sql = "INSERT INTO user (username, password) VALUES ('user1', 'pass1')";
        stmt.execute(sql);

        sql = "INSERT INTO user (username, password) VALUES ('user2', 'pass2')";
        stmt.execute(sql);




        ResultSet rs;
        sql = "SELECT COUNT(*) FROM user WHERE password = 'pass3'";
        rs = stmt.executeQuery(sql);

        if (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("count: " + count);
            if (count > 0) {
                System.out.println("Password exists in the database.");
            } else {
                System.out.println("Password does not exist in the database.");
            }
        }

        stmt.close();
        con.close();

*/
    }
}
