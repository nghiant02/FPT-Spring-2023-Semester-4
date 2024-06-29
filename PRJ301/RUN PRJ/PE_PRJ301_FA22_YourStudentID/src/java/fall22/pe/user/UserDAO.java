/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fall22.pe.user;

import fall22.pe.utils.DBUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Hoadnt
 */
public class UserDAO extends DBUtils{
public UserDTO findAccount(String username, String password) throws ClassNotFoundException {
        try {
            String sql = "select * from tblUsers where userID = ? and password = ?";
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("loi function find account");
        }
        return null;
    }
    
    
    
}
