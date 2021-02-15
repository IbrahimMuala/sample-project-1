package service.impl;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import config.AeroSpikeConfig;
import model.Appointment;
import model.Patient;
import service.PatientService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class PatientServiceImp implements PatientService {

    AerospikeClient client = AeroSpikeConfig.getAeroSpikeClient();

    @Override public Patient createNewPatient(Patient patient) {

        Key key = new Key("test", "patient", patient.getId());
        Bin id = new Bin("id", patient.getId());
        Bin firstName = new Bin("firstName", patient.getFirstName());
        Bin lastName = new Bin("lastName", patient.getLastName());
        Bin userName = new Bin("userName", patient.getUserName());
        Bin appointments = new Bin("appointments", new ArrayList<Appointment>());

        client.put(null, key, id, firstName, lastName, userName, appointments);
        return patient;
    }

    @Override public List<Patient> getAllPatient() {
        Statement statement = new Statement();
        statement.setNamespace("test");
        statement.setSetName("patient");
        RecordSet records = client.query(null, statement);

        List<Patient> patients = new ArrayList<>();

        while (records.next()) {
            Record currentRecord = records.getRecord();

            Patient patient = new Patient();
            patient.setId(currentRecord.getLong("id"));
            patient.setFirstName(currentRecord.getString("firstName"));
            patient.setLastName(currentRecord.getString("lastName"));
            patient.setUserName(currentRecord.getString("userName"));
            patient.setAppointments((List<Appointment>) currentRecord.getList("appointments"));

            patients.add(patient);
        }
        return patients;
    }

    @Override public Boolean deletePatientById(Long id) {
        Key key = new Key("test", "patient", id);
        client.delete(null, key);
        return true;
    }
}
