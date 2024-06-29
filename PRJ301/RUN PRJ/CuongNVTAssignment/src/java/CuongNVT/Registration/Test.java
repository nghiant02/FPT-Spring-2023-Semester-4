
package CuongNVT.Registration;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(RegistrationDAO.updateAccount("starcidaphilosopher1","CUONGDZ"));
        } catch (SQLException | NamingException | ClassNotFoundException ex) {
            
        }
        
        
        RegistrationFaultCatch RFC = new RegistrationFaultCatch();
        System.out.println(RFC.updateUsernameFault("nhoc_dddgx2", 6, 20));
        System.out.println(RFC.updatePasswordFault("123456", 6, 20));
        System.out.println(RFC.updateConfirmFault("123456", "123456"));
    }
}
