package CuongNVT.Book;

import CuongNVT.Utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

public class BookDAO implements Serializable {

    public static BookDTO getBook(String ID) throws
            SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "SELECT ID, Name, Author, Description, ImgUrl, Price "
                + "FROM Book "
                + "WHERE ID = ?";

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, ID);
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                BookDTO acc
                        = new BookDTO(ReS.getString(1), ReS.getString(2), ReS.getString(3), ReS.getString(4), ReS.getString(5), ReS.getInt(6));
                return acc;

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
        return null;

    }

    public static ArrayList<BookDTO> getBooks(String ID)
            throws SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "SELECT ID, Name, Author, Description, ImgUrl, Price "
                + "FROM Book "
                + "WHERE ID LIKE ?";

        ArrayList<BookDTO> books = new ArrayList<>();
        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            if (ID.equalsIgnoreCase("all")) {
                ID = "";
            }
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, "%" + ID + "%");

            ReS = PreS.executeQuery();
            while (ReS.next()) {
                BookDTO acc
                        = new BookDTO(ReS.getString(1), ReS.getString(2), ReS.getString(3), ReS.getString(4), ReS.getString(5), ReS.getInt(6));
                books.add(acc);

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
        return books;

    }

}
