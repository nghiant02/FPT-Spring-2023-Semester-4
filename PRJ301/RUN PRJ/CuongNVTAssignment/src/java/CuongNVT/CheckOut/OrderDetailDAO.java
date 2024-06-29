package CuongNVT.CheckOut;

import CuongNVT.Book.BookDTO;
import CuongNVT.Cart.CartObject;
import CuongNVT.Utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

public class OrderDetailDAO implements Serializable {
       public static boolean addOrderDetails(String OrderID, CartObject cart)
            throws SQLException, ClassNotFoundException, NamingException {
        String SQLQuery = "INSERT INTO "
                + "OrderDetail(BookID, OrderID, Quantity) "
                + "VALUES (?,?,?)";
        
        Connection conn = null;
        PreparedStatement PreS = null;
        boolean flag = true;
        try {
            conn = DBHelper.makeConnect();
            for (String key : cart.getItems().keySet()){
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, key);
            PreS.setString(2, OrderID);
            PreS.setInt(3, cart.getItems().get(key));
            final int AffectedRow = PreS.executeUpdate();
            if (AffectedRow == 0){
            flag = false;
            }
            }
            
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            } 
        }
        return flag;
    }
}
