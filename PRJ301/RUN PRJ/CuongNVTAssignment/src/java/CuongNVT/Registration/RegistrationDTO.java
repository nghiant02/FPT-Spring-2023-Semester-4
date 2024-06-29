
package CuongNVT.Registration;

import java.io.Serializable;

public class RegistrationDTO implements Serializable {
    private String Username;
    private String Password;
    private Boolean Role;
    private String FullName;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String Username, String Password, Boolean Role, String FullName) {
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
        this.FullName = FullName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Boolean getRole() {
        return Role;
    }

    public void setRole(Boolean Role) {
        this.Role = Role;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }
    
}
