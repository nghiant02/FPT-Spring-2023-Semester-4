
package nghiant.registration;

import java.io.Serializable;


public class RegistrationDeleteError implements Serializable {

    private String deleteCurrentAccount;

    /**
     * @return the deleteCurrentAccount
     */
    public String getDeleteCurrentAccount() {
        return deleteCurrentAccount;
    }

    /**
     * @param deleteCurrentAccount the deleteCurrentAccount to set
     */
    public void setDeleteCurrentAccount(String deleteCurrentAccount) {
        this.deleteCurrentAccount = deleteCurrentAccount;
    }
}
