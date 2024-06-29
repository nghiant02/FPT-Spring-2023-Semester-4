/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates/. Created on : 30-10-2022
 * and open the template in the editor.
 */
package fall22.pe.food;

import fall22.pe.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hd
 */
public class FoodDAO {

    public List<FoodDTO> searchProductByName(String txtSearch) throws ClassNotFoundException, SQLException {
        List<FoodDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM tblFoods"
                        + " WHERE name like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + txtSearch + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new FoodDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6)));
                }
            }
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

//    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
//        List<FoodDTO> list = new FoodDAO().searchProductByName("a");
//        for (FoodDTO foodDTO : list) {
//            System.out.println(foodDTO.toString());
//        }
//    }
}
