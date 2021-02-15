package service.impl;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import config.AeroSpikeConfig;
import model.Appointment;
import model.Doctor;
import service.DoctorService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class DoctorServiceImp implements DoctorService {

    AerospikeClient client = AeroSpikeConfig.getAeroSpikeClient();

    @Override public Doctor createNewDoctor(Doctor doctor) {

        Key key = new Key("test", "doctor", doctor.getId());
        Bin id = new Bin("id", doctor.getId());
        Bin firstName = new Bin("firstName", doctor.getFirstName());
        Bin lastName = new Bin("lastName", doctor.getLastName());
        Bin userName = new Bin("userName", doctor.getUserName());
        Bin appointments = new Bin("appointments", new ArrayList<Appointment>());

        client.put(null, key, id, firstName, lastName, userName, appointments);
        return doctor;
    }

    @Override public List<Doctor> getAllDoctors() {
        Statement statement = new Statement();
        statement.setNamespace("test");
        statement.setSetName("doctor");
        RecordSet records = client.query(null, statement);

        List<Doctor> doctors = new ArrayList<>();

        while(records.next()) {
            Record currentRecord = records.getRecord();

            Doctor doctor = new Doctor();
            doctor.setId(currentRecord.getLong("id"));
            doctor.setFirstName(currentRecord.getString("firstName"));
            doctor.setLastName(currentRecord.getString("lastName"));
            doctor.setUserName(currentRecord.getString("userName"));
            doctor.setAppointments((List<Appointment>) currentRecord.getList("appointments"));

            doctors.add(doctor);
        }
        return doctors;
    }

    @Override public Boolean deleteDoctorById(Long id) {
        Key key = new Key("test", "doctor", id);
        client.delete(null, key);
        return true;
    }
}
