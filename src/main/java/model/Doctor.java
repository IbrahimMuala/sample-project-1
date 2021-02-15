package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) @Data
public class Doctor extends User {

    public Doctor() {
        super();
    }

    public Doctor(Long id, String firstName, String lastName, String userName) {
        super(id, firstName, lastName, userName);
    }
}
