package net.etfbl.indeks.dto;

import net.etfbl.indeks.security.roles.Roles;

public class UpdateAccountDTO {

    private String email;
    private String password;

    public UpdateAccountDTO() {
    }

    public UpdateAccountDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UpdateAccountDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
