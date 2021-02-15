package controller.rest;

import model.Patient;
import service.PatientService;
import service.impl.PatientServiceImp;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/patient")
@ApplicationScoped

public class PatientController {

    PatientService patientService = new PatientServiceImp();

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Patient createNewDoctor(Patient patient) {
        return patientService.createNewPatient(patient);
    }

    @GET
    public Response getAllPatients() {
        return Response.ok(patientService.getAllPatient()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDoctorById(@PathParam("id") Long id) {
        return Response.ok(patientService.deletePatientById(id)).build();
    }
}
