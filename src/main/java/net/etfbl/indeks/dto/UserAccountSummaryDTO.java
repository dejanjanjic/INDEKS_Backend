package net.etfbl.indeks.dto;

public class UserAccountSummaryDTO {
    private Long id;  // Add ID field
    private String firstName;
    private String lastName;
    private Boolean active;

    // Constructors
    public UserAccountSummaryDTO() {
    }

    public UserAccountSummaryDTO(Long id, String firstName, String lastName, Boolean active) {
        this.id = id;  // Initialize ID
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
