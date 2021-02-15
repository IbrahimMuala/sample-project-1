package model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private List<Appointment> appointments;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }
}
