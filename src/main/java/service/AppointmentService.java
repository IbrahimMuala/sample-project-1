package service;

import model.Appointment;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Path;
import java.util.List;

public interface AppointmentService {

    Appointment createNewAppointment(Appointment appointment);

    List<Appointment> getAllAppointment();

    Boolean deleteAllAppointments();
}
