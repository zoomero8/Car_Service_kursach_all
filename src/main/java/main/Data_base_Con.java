package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Data_base_Con extends Configs{
    private static Connection db_con;

    public static Connection getDb_con()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver" );
        db_con = DriverManager.getConnection(
                "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME,
                DB_USER, DB_PASS);
        return db_con;
    }

    public static synchronized Connection getInstance() throws SQLException, ClassNotFoundException {
        if (db_con == null) {
            db_con = getDb_con();
        }
        return db_con;
    }
}