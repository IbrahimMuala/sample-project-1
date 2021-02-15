import service.impl.AppointmentServiceImp;
import service.impl.DoctorServiceImp;
import service.impl.PatientServiceImp;

import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        try {
            Endpoint.publish("http://localhost:8000/v1/soap/appointment", new AppointmentServiceImp());
            Endpoint.publish("http://localhost:8000/v1/soap/doctor", new DoctorServiceImp());
            Endpoint.publish("http://localhost:8000/v1/soap/patient", new PatientServiceImp());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
