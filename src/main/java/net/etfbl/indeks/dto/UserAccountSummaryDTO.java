package net.etfbl.indeks.dto;

public class UserAccountSummaryDTO {
    private String firstName;
    private String lastName;
    private Boolean active;

    // Constructors
    public UserAccountSummaryDTO() {
    }

    public UserAccountSummaryDTO(String firstName, String lastName, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
