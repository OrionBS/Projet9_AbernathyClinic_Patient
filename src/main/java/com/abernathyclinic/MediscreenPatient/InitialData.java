package com.abernathyclinic.MediscreenPatient;

import com.abernathyclinic.MediscreenPatient.models.Patient;
import com.abernathyclinic.MediscreenPatient.services.PatientService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class InitialData {

    private final PatientService patientService;

    public InitialData(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostConstruct
    public void creatingPatients() {
        Patient patient1 = new Patient("John", "Smith", LocalDate.of(1989, 11, 25), "M", "P.O. Box 570, 5900 Purus, Rd.", "(769) 346-4394");
        Patient patient2 = new Patient("Aurelia", "Gomez", LocalDate.of(1978, 9, 12), "F", "9474 Purus Ave", "(858) 996-5690");
        Patient patient3 = new Patient("Joelle", "Foley", LocalDate.of(1985, 2, 3), "F", "P.O. Box 823, 6866 Erat Street", "1-896-773-7888");
        Patient patient4 = new Patient("Orion", "Beauny", LocalDate.of(2000, 2, 3), "M", "Somewhere", "Some number");
        List<Patient> patients = Arrays.asList(patient1, patient2, patient3, patient4);
        for (Patient patient : patients) {
            if (patientService.readingPatientByFirstNameAndLastName(patient.getFirstName(), patient.getLastName()) == null) {
                patientService.creatingPatient(patient);
            }
        }
    }

    @PostConstruct
    public void readingPatients() {
        patientService.readingAllPatient().forEach(patient -> System.out.println(patient.toString()));
    }


}
