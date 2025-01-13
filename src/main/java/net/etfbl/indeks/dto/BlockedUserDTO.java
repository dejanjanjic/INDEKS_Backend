package net.etfbl.indeks.dto;

import java.time.LocalDateTime;

public class BlockedUserDTO {
    private String firstName;
    private String lastName;
    private LocalDateTime dateBlocked;

    public BlockedUserDTO(String firstName, String lastName, LocalDateTime dateBlocked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBlocked = dateBlocked;
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

    public LocalDateTime getDateBlocked() {
        return dateBlocked;
    }

    public void setDateBlocked(LocalDateTime dateBlocked) {
        this.dateBlocked = dateBlocked;
    }

    @Override
    public String toString() {
        return "BlockedUserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateBlocked=" + dateBlocked +
                '}';
    }
}
