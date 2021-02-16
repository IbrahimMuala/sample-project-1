package controller;

import model.Doctor;
import service.DoctorService;
import service.impl.DoctorServiceImp;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/doctor")
@ApplicationScoped
public class DoctorController {

    DoctorService doctorService = new DoctorServiceImp();

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Doctor createNewDoctor(Doctor doctor) {
       return doctorService.createNewDoctor(doctor);
    }

    @GET
    public Response getAllDoctors() {
        return Response.ok(doctorService.getAllDoctors()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDoctorById(@PathParam("id") Long id) {
        return Response.ok(doctorService.deleteDoctorById(id)).build();
    }
}
