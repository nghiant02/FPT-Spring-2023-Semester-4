/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.spring23.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.spring23.utils.DBUtils;

/**
 *
 * @author hd
 */
public class DAO {

    public List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "select * from tblProducts where status = 1";
            PreparedStatement stm = cn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
