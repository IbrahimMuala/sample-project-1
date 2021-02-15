package model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Appointment implements Serializable {
    private static final long serialVersionUID = 3383146991726936858L;
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long doctor;

    private Long patient;
}
