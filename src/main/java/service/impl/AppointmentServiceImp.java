package service.impl;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.query.Filter;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import config.AeroSpikeConfig;
import model.Appointment;
import model.Doctor;
import service.AppointmentService;

import javax.inject.Singleton;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService
public class AppointmentServiceImp implements AppointmentService {

    AerospikeClient client = AeroSpikeConfig.getAeroSpikeClient();

    @Override public Appointment createNewAppointment(Appointment appointment) {

        Key key = new Key("test", "appointment", appointment.getId());
        Bin id = new Bin("id", appointment.getId());
        Bin fromDate = new Bin("fromDate", appointment.getFromDate());
        Bin toDate = new Bin("toDate", appointment.getToDate());
        Bin doctor = new Bin("doctor", appointment.getDoctor());
        Bin patient = new Bin("patient", appointment.getPatient());
        client.put(null, key, id, fromDate, toDate, doctor, patient);

        storeAppointmentForPatient(appointment);

        storeAppointmentForDoctor(appointment);

        return appointment;
    }

    @Override public List<Appointment> getAllAppointment() {
        Statement statement = new Statement();
        statement.setNamespace("test");
        statement.setSetName("appointment");
        RecordSet records = client.query(null, statement);

        List<Appointment> appointments = new ArrayList<>();

        while(records.next()) {
            Record currentRecord = records.getRecord();

            Appointment appointment = new Appointment();
            appointment.setId(currentRecord.getLong("id"));
            appointment.setDoctor(currentRecord.getLong("doctor"));
            appointment.setPatient(currentRecord.getLong("patient"));
            appointment.setFromDate((Date) currentRecord.getValue("fromDate"));
            appointment.setToDate((Date) currentRecord.getValue("toDate"));

            appointments.add(appointment);
        }
        return appointments;
    }

    private void storeAppointmentForPatient(Appointment appointment) {
        Key key = new Key("test", "patient", appointment.getPatient());

        storeForUser(appointment, key);
    }

    private void storeAppointmentForDoctor(Appointment appointment) {
        Key key = new Key("test", "doctor", appointment.getDoctor());

        storeForUser(appointment, key);
    }

    private void storeForUser(Appointment appointment, Key key) {
        Record record = client.get(null, key, "appointments");

        List<Appointment> appointments = (List<Appointment>) record.getList("appointments");
        if(appointments == null) {
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
        Bin appointmentsWrite = new Bin("appointments", appointments);
        client.put(null, key, appointmentsWrite);
    }

    @Override public Boolean deleteAllAppointments() {
        Statement statement = new Statement();
        statement.setNamespace("test");
        statement.setSetName("appointment");
        RecordSet records = client.query(null, statement);

        while(records.next()) {
            Record currentRecord = records.getRecord();
            Appointment appointment = new Appointment();
            appointment.setId(currentRecord.getLong("id"));
            Key key = new Key("test", "appointment", currentRecord.getLong("id"));
            client.delete(null, key);
        }
        return true;
    }
}
