package CuongNVT.Registration;

import CuongNVT.Utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

public class RegistrationDAO implements Serializable {

    public static RegistrationDTO getAccount(String Username) throws
            SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "SELECT Username, Password, Role, FullName "
                + "FROM Registration "
                + "WHERE Username = ?";

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, Username);
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                RegistrationDTO acc
                        = new RegistrationDTO(ReS.getString(1), ReS.getString(2), ReS.getBoolean(3), ReS.getString(4));
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

    public static RegistrationDTO getAccount(String Username, String Password) throws
            SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "SELECT Username, Password, Role, FullName "
                + "FROM Registration "
                + "WHERE Username = ? AND Password = ?";

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, Username);
            PreS.setString(2, Password);
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                RegistrationDTO acc
                        = new RegistrationDTO(ReS.getString(1), ReS.getString(2), ReS.getBoolean(3), ReS.getString(4));
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

    public static ArrayList<RegistrationDTO> getAccounts(String Username)
            throws SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "SELECT Username, Password, Role, FullName "
                + "FROM Registration "
                + "WHERE Username LIKE ?";
        
        ArrayList<RegistrationDTO> accounts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            if (Username.equalsIgnoreCase("all")){
                Username = "";
            }
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, "%" + Username + "%");

            ReS = PreS.executeQuery();
            while (ReS.next()) {
                RegistrationDTO acc = new RegistrationDTO(ReS.getString(1), ReS.getString(2), ReS.getBoolean(3), ReS.getString(4));
                accounts.add(acc);

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
        return accounts;

    }

    public static boolean insertAccount(String Username, String Password, boolean Role, String FullName)
            throws SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "INSERT INTO Registration"
                + "(Username, Password, Role, FullName) "
                + "VALUES (?,?,?,?) ";

        Connection conn = null;
        PreparedStatement PreS = null;
        boolean allowtoinsert = false;
        try {
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, Username);
            PreS.setString(2, Password);
            PreS.setBoolean(3, Role);
            PreS.setString(4, FullName);
            final int affectedRow = PreS.executeUpdate();
            if (affectedRow > 0) {
                allowtoinsert = true;
            }
        } catch (SQLException ex) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            }

        }
        return allowtoinsert;

    }

    public static boolean deleteAccount(String Username)
            throws SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "DELETE FROM Registration "
                + "WHERE Username = ?";
        Connection conn = null;
        PreparedStatement PreS = null;
        boolean allowtoinsert = false;
        try {
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, Username);
            final int affectedRow = PreS.executeUpdate();
            if (affectedRow > 0) {
                allowtoinsert = true;
            }
        } catch (SQLException ex) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            }

        }
        return allowtoinsert;

    }

    public static boolean updateAccount(String Username, String FullName)
            throws SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "UPDATE Registration "
                          +"SET FullName = ? "
                          +"WHERE Username = ?";
        Connection conn = null;
        PreparedStatement PreS = null;
        boolean allowtoinsert = false;
        try {
            conn = DBHelper.makeConnect();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, FullName);
            PreS.setString(2, Username);
            final int affectedRow = PreS.executeUpdate();
            if (affectedRow > 0) {
                allowtoinsert = true;
            }
        } catch (SQLException ex) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            }

        }
        return allowtoinsert;

    }

}
