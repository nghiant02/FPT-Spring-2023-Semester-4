package cuongnvt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBHelper {

    public static Connection getConnection()
            throws SQLException, NamingException {
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("cuongnvtDataSource");
        Connection con = ds.getConnection();
        return con;
    }

    public static Connection oldGetConnection()
            throws SQLException, ClassNotFoundException {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=PracticalTest";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, "sa", "12345");
    }
}
