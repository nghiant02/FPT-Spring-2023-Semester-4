package cuongnvt.user;


import cuongnvt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tucuo
 */
public class UserDAO implements Serializable{
     public boolean checkLogin(String username, String password) throws
            SQLException, ClassNotFoundException {
        String SQLQuery = "SELECT * FROM tbl_User WHERE userID = ? AND password = ?";
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        boolean flag = false;
        try {
            conn = DBHelper.oldGetConnection();
            pre = conn.prepareCall(SQLQuery);
            pre.setString(1, username);
            pre.setString(2, password);
            res = pre.executeQuery();
            while (res.next()) {
               UserDTO account
                        = new UserDTO(res.getString(1), res.getString(2), res.getString(3), res.getBoolean(4));
                flag = true;

            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (res != null) {
                res.close();
            }

        }
        return flag;

    }
}
