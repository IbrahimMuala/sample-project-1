package controller.rest;

import model.Doctor;
import service.DoctorService;
import service.impl.DoctorServiceImp;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/doctor")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorController {

    DoctorService doctorService = new DoctorServiceImp();

    @POST
    @Path("/register")
    public Doctor createNewDoctor(Doctor doctor) {
        System.out.println("hello");
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
