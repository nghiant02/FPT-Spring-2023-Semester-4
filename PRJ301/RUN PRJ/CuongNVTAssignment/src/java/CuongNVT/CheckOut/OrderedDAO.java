package CuongNVT.CheckOut;

import CuongNVT.Book.BookDAO;
import CuongNVT.Book.BookDTO;
import CuongNVT.Cart.CartObject;
import CuongNVT.Utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderedDAO implements Serializable {

    private static ArrayList<String> getOrders()
            throws ClassNotFoundException, SQLException, NamingException {
        ArrayList<String> OrderIDs = new ArrayList<>();
        String SQLQuery = "SELECT OrderID "
                + "FROM Ordered ";

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;
        try {
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                String str = ReS.getString(1);
                OrderIDs.add(str);
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            }
            if (ReS != null) {
                ReS.close();
            }

        }
        return OrderIDs;
    }

    private static String generateOrderID() {
        ArrayList<String> OrderIDs = null;
        try {
            OrderIDs = getOrders();
        } catch (ClassNotFoundException | SQLException | NamingException ex) {
        }
        ArrayList<Integer> OrderIDValues = new ArrayList<>();
        for (String o : OrderIDs) {
            Integer x = null;
            try {
                x = Integer.parseInt(o.substring(1));
            } catch (NumberFormatException ex) {
            }
            if (x != null) {
                OrderIDValues.add(x);
            }
        }
        int returnvalue = 1;
        boolean flag;
        do {
            flag = false;
            for (Integer i : OrderIDValues) {
                if (i == returnvalue) {
                    returnvalue++;
                    flag = true;
                }
            }

        } while (flag);
        String stringTransform = Integer.toString(returnvalue);
        if (stringTransform.length() > 3) {
            return null;
        }
        int numberof0 = 3 - stringTransform.length();
        if (numberof0 > 0) {
            for (int i = 1; i <= numberof0; i++) {
                stringTransform = "0" + stringTransform;
            }
        }
        stringTransform = "S" + stringTransform;
        return stringTransform;
    }
    
    public static int TotalPayment(CartObject cart){
        int total = 0;
        for (String key : cart.getItems().keySet()){
           
         BookDTO book = null;
            try {
                book = BookDAO.getBook(key);
            } catch (SQLException | NamingException | ClassNotFoundException ex) {}
         total += book.getPrice() * cart.getItems().get(key);  
        }
        return total;
    }
    
    public static String addOrder(String Username, int total)
            throws ClassNotFoundException, SQLException, NamingException {
        
        String OrderID = generateOrderID();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
  
        String SQLQuery = "INSERT INTO Ordered(OrderID , OrderDate, Username, TotalPayment) "
                + "VALUES (?,?,?,?);";
        Connection conn = null;
        PreparedStatement PreS = null;
        Boolean flag = false;
        try {
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, OrderID);
            PreS.setDate(2, date);
            PreS.setString(3, Username);
            PreS.setInt(4, total);
            final int AffectedRow = PreS.executeUpdate();
            if (AffectedRow > 0) {
                flag = true;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            }

        }
        if (flag) {
            return OrderID;
        } else {
            return null;
        }
    }
}
