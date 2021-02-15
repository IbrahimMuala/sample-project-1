package service;

import model.Patient;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

public interface PatientService {

    Patient createNewPatient(Patient patient);

    List<Patient> getAllPatient();

    Boolean deletePatientById(Long id);
}
