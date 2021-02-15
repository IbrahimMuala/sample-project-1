package service;

import model.Doctor;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.print.Doc;
import java.util.List;

public interface DoctorService {

    Doctor createNewDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Boolean deleteDoctorById(Long id);
}
