package controller;

import model.Appointment;
import service.AppointmentService;
import service.impl.AppointmentServiceImp;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/appointment")
@ApplicationScoped
public class AppointmentController {
    AppointmentService appointmentService = new AppointmentServiceImp();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Appointment createNewAppointment(Appointment appointment) {
        return appointmentService.createNewAppointment(appointment);
    }

    @GET
    public Response getAllAppointments() {
        return Response.ok(appointmentService.getAllAppointment()).build();
    }

    @DELETE
    public Response deleteAllAppointments() {
        return Response.ok(appointmentService.deleteAllAppointments()).build();
    }
}
