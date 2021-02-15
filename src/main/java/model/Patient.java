package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) @Data
public class Patient extends User {

    public Patient() {
        super();
    }

    public Patient(Long id, String firstName, String lastName, String userName) {
        super(id, firstName, lastName, userName);
    }
}
