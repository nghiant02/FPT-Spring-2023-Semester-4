/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.spring23.shopping;

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
public class DAO {

    public List<Product> searchProducts() throws ClassNotFoundException, SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = pe.spring23.utils.DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM tblProducts"
                        + " where status = 1";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5)));
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

    public Product searchProductByID(String id) throws ClassNotFoundException, SQLException {
        Product list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = pe.spring23.utils.DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [tblProducts]"
                        + " where productID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1,  id );
                rs = stm.executeQuery();
                while (rs.next()) {
                    
                   list =  new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5));
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

    }

