
package CuongNVT.Book;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class Test {
    public static void main(String[] args) {
        try {
            System.out.print(BookDAO.getBooks("KVH").size());
        } catch (SQLException | NamingException | ClassNotFoundException ex) {
        
        }
    }
}