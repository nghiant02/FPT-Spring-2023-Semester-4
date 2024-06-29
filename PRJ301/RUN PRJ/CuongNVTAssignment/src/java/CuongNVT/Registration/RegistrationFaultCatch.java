package CuongNVT.Registration;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class RegistrationFaultCatch implements Serializable {

    private String Username_Fault_Msg;
    private String Password_Fault_Msg;
    private String Confirm_Fault_Msg;
    private String FullName_Fault_Msg;

    public RegistrationFaultCatch() {
        Username_Fault_Msg = "";
        Password_Fault_Msg = "";
        Confirm_Fault_Msg = "";
        FullName_Fault_Msg = "";
    }

    public String getUsername_Fault_Msg() {
        return Username_Fault_Msg;
    }

    public void setUsername_Fault_Msg(String Username_Fault_Msg) {
        this.Username_Fault_Msg = Username_Fault_Msg;
    }

    public String getPassword_Fault_Msg() {
        return Password_Fault_Msg;
    }

    public void setPassword_Fault_Msg(String Password_Fault_Msg) {
        this.Password_Fault_Msg = Password_Fault_Msg;
    }

    public String getConfirm_Fault_Msg() {
        return Confirm_Fault_Msg;
    }

    public void setConfirm_Fault_Msg(String Confirm_Fault_Msg) {
        this.Confirm_Fault_Msg = Confirm_Fault_Msg;
    }

    public String getFullName_Fault_Msg() {
        return FullName_Fault_Msg;
    }

    public void setFullName_Fault_Msg(String FullName_Fault_Msg) {
        this.FullName_Fault_Msg = FullName_Fault_Msg;
    }

    public boolean updateUsernameFault(String Username, int min, int max) {
        boolean flag = false;
        if (Username.length() < min) {
            Username_Fault_Msg
                    = " Your typed Username: " + Username
                    + " is too short (MUST at least " + min + " characters).";
            flag = true;
        } else if (Username.length() > max) {
            Username_Fault_Msg
                    = " Your typed Username: " + Username
                    + " is too long (MUST at most " + max + " characters).";
            flag = true;
        } else if (Password_Fault_Msg.equals("") 
                && Confirm_Fault_Msg.equals("") 
                && FullName_Fault_Msg.equals("") ) {
            RegistrationDTO acc = null;
            try {
                acc = RegistrationDAO.getAccount(Username);
            } catch (SQLException | NamingException | ClassNotFoundException ex) {       
            }
            if (acc != null){
                Username_Fault_Msg 
                      = "Your typed Username: " + Username
                      + " is duplicated with another Account's one.";
            flag = true;
            }

        }

        return flag;
    }

    public boolean updatePasswordFault(String Password, int min, int max) {
        boolean flag = false;
        if (Password.length() < min) {
            Password_Fault_Msg
                    = " Your typed Password is too short (MUST at least " + min + " characters).";
            flag = true;
        }
        if (Password.length() > max) {
            Password_Fault_Msg
                    = " Your typed Password: " + Password
                    + " is too long (MUST at most " + max + " characters).";
            flag = true;
        }
        return flag;
    }

    public boolean updateConfirmFault(String Confirm, String Password) {
        boolean flag = false;
        if (!Confirm.equals(Password)) {
            Confirm_Fault_Msg
                    = "Your typed Confirmed Password is not match with your Password.";
            flag = true;
        }

        return flag;
    }

    public boolean updateFullNameFault(String FullName, int min, int max) {
        boolean flag = false;
        if (FullName.length() < min) {
            FullName_Fault_Msg
                    = " Your typed Full Name: " + FullName
                    + " is too short (MUST at least " + min + " characters).";
            flag = true;
        }
        if (FullName.length() > max) {
            FullName_Fault_Msg
                    = " Your typed Full Name: " + FullName
                    + " is too long (MUST at most " + max + " characters).";
            flag = true;
        }
        return flag;
    }

}
