
package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

    private static final String URL =
     "jdbc:postgresql://localhost:5432/DBTP1";

 private static final String USER = System.getenv("DB_USER");
private static final String PASSWORD = System.getenv("DB_PASSWORD");
 
    public Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver introuvable", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


