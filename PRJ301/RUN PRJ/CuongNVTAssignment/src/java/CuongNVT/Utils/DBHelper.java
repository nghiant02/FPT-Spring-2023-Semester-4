package CuongNVT.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    public static Connection makeConnect()
            throws ClassNotFoundException, SQLException {
        String instance = "";
        String serverName = "localhost";
        String portNumber = "1433";
        String dbName = "BTVN";
        String userID = "sa";
        String password = "12345";
        String url;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        } else {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return DriverManager.getConnection(url, userID, password);

    }
}
