
package cuongnvt.utils;

import java.sql.SQLException;

public class TestConnect {
    public static void main(String[] args) 
            throws SQLException, ClassNotFoundException {
        DBHelper.oldGetConnection();
    }
}
