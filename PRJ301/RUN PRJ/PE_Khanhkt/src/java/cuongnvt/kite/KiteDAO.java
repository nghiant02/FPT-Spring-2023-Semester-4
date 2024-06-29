/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongnvt.kite;

import cuongnvt.user.UserDTO;
import cuongnvt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author tucuo
 */
public class KiteDAO implements Serializable {

    public List<KiteDTO> getKites(String searchValue)
            throws SQLException, ClassNotFoundException {
        String SQLQuery = "SELECT * FROM tbl_Kite WHERE id LIKE ?";

        List<KiteDTO> kites = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet res = null;

        try {

            conn = DBHelper.oldGetConnection();
            pre = conn.prepareCall(SQLQuery);
            pre.setString(1, "%" + searchValue + "%");

            res = pre.executeQuery();
            while (res.next()) {
                KiteDTO kite =  new KiteDTO(res.getString(1), res.getString(2), res.getString(3),
                res.getString(4),res.getString(5),res.getInt(6), res.getBoolean(7),
                   res.getInt(8),res.getFloat(9));
                kites.add(kite);

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
        return kites;

    }

     public boolean updateKite( String id, float price, int quantity, boolean status)
            throws SQLException, ClassNotFoundException {
        String SQLQuery = "UPDATE tbl_Kite "
                + "SET price = ?, quantity = ?, status = ? "
                + "WHERE id = ?";
        Connection conn = null;
        PreparedStatement pre = null;
        boolean allowtoinsert = false;
        try {
            conn = DBHelper.oldGetConnection();
            pre = conn.prepareCall(SQLQuery);
            pre.setFloat(1, price);
            pre.setInt(2, quantity);
            pre.setBoolean(3, status);
             pre.setString(4, id);
            final int affectedRow = pre.executeUpdate();
            if (affectedRow > 0) {
                allowtoinsert = true;
            }
        } catch (SQLException ex) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pre != null) {
                pre.close();
            }

        }
        return allowtoinsert;

    }

}
