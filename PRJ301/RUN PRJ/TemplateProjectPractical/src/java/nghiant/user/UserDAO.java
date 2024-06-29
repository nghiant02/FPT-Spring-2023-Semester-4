/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiant.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khanhkt.account.AccountDTO;
import khanhkt.utils.DBHelper;

/**
 *
 * @author nghia
 */
public class UserDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws
            SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO dto = null;
        boolean flag = false;
        try {
            //1. connect database
            con = DBHelper.getConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "SELECT * FROM tbl_User WHERE userID = ? AND password = ?";
                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Query Data 
                rs = stm.executeQuery();
                //5.Process Data
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    dto = new UserDTO(username, password, fullname);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return flag;
    }
}
