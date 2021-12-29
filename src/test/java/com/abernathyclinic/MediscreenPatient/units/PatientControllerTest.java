package com.abernathyclinic.MediscreenPatient.units;

import com.abernathyclinic.MediscreenPatient.controllers.PatientController;
import com.abernathyclinic.MediscreenPatient.models.Patient;
import com.abernathyclinic.MediscreenPatient.services.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientServiceImpl patientService;

    @Test
    public void testCreatingPatient() {
        //GIVEN
        Patient patient = new Patient("testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.creatingPatient(patient)).thenReturn(patient);

        //WHEN
        ResponseEntity<Object> patientSave = patientController.creatingPatient(patient);

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).creatingPatient(patient);
        Assertions.assertEquals(patient,patientSave.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, patientSave.getStatusCode());

    }

    @Test
    public void testCreatingPatientWithError() {
        //GIVEN
        Patient patient = new Patient("testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.creatingPatient(patient)).thenReturn(null);

        //WHEN
        ResponseEntity<Object> patientSave = patientController.creatingPatient(patient);

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).creatingPatient(patient);
        Assertions.assertEquals("Erreur, patient déjà existant ou informations incomplètes.",patientSave.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, patientSave.getStatusCode());

    }

    @Test
    public void testReadingPatientByFirstNameAndLastName() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.readingPatientByFirstNameAndLastName(patient.getFirstName(),patient.getLastName())).thenReturn(patient);

        //WHEN
        ResponseEntity<Object> patientRead = patientController.readingPatientByFirstNameAndLastName(patient.getFirstName(),patient.getLastName());

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).readingPatientByFirstNameAndLastName(patient.getFirstName(),patient.getLastName());
        Assertions.assertEquals(patient,patientRead.getBody());
        Assertions.assertEquals(HttpStatus.OK, patientRead.getStatusCode());

    }

    @Test
    public void testReadingPatientByFirstNameAndLastNameWithError() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.readingPatientByFirstNameAndLastName(patient.getFirstName(),patient.getLastName())).thenReturn(null);

        //WHEN
        ResponseEntity<Object> patientRead = patientController.readingPatientByFirstNameAndLastName(patient.getFirstName(),patient.getLastName());

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).readingPatientByFirstNameAndLastName(patient.getFirstName(),patient.getLastName());
        Assertions.assertEquals("Erreur, patient inexistant ou paramètres incorrects.",patientRead.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, patientRead.getStatusCode());

    }

    @Test
    public void testReadingPatientById() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.readingPatientById(patient.getId())).thenReturn(patient);

        //WHEN
        ResponseEntity<Object> patientRead = patientController.readingPatientById(patient.getId());

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).readingPatientById(patient.getId());
        Assertions.assertEquals(patient,patientRead.getBody());
        Assertions.assertEquals(HttpStatus.OK, patientRead.getStatusCode());

    }

    @Test
    public void testReadingPatientByIdWithError() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.readingPatientById(patient.getId())).thenReturn(null);

        //WHEN
        ResponseEntity<Object> patientRead = patientController.readingPatientById(patient.getId());

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).readingPatientById(patient.getId());
        Assertions.assertEquals("Erreur, patient inexistant ou paramètres incorrects.",patientRead.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, patientRead.getStatusCode());

    }

    @Test
    public void testUpdatingPatient() {
        //GIVEN
        Patient patient = new Patient("testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.updatingPatient(patient)).thenReturn(patient);

        //WHEN
        ResponseEntity<Object> patientUpdated = patientController.updatingPatient(patient);

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).updatingPatient(patient);
        Assertions.assertEquals(patient,patientUpdated.getBody());
        Assertions.assertEquals(HttpStatus.OK, patientUpdated.getStatusCode());

    }

    @Test
    public void testUpdatingPatientWithError() {
        //GIVEN
        Patient patient = new Patient("testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.updatingPatient(patient)).thenReturn(null);

        //WHEN
        ResponseEntity<Object> patientUpdated = patientController.updatingPatient(patient);

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).updatingPatient(patient);
        Assertions.assertEquals("Erreur, informations incomplètes.",patientUpdated.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, patientUpdated.getStatusCode());

    }

    @Test
    public void testDeletingPatient() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.deletingPatient(patient.getFirstName(),patient.getLastName())).thenReturn(true);

        //WHEN
        ResponseEntity<String> patientDeleted = patientController.deletingPatient(patient.getFirstName(),patient.getLastName());

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).deletingPatient(patient.getFirstName(),patient.getLastName());
        Assertions.assertEquals("Patient correctement effacé.",patientDeleted.getBody());
        Assertions.assertEquals(HttpStatus.OK, patientDeleted.getStatusCode());

    }

    @Test
    public void testDeletingPatientWithError() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientService.deletingPatient(patient.getFirstName(),patient.getLastName())).thenReturn(false);

        //WHEN
        ResponseEntity<String> patientRead = patientController.deletingPatient(patient.getFirstName(),patient.getLastName());

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).deletingPatient(patient.getFirstName(),patient.getLastName());
        Assertions.assertEquals("Erreur, patient inexistant ou paramètres incorrectes.",patientRead.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, patientRead.getStatusCode());

    }

    @Test
    public void testReadingAllPatient() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        List<Patient> patients = Collections.singletonList(patient);
        Mockito.when(patientService.readingAllPatient()).thenReturn(patients);

        //WHEN
        ResponseEntity<Object> patientsRead = patientController.readingAllPatient();

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).readingAllPatient();
        Assertions.assertEquals(patients,patientsRead.getBody());
        Assertions.assertEquals(HttpStatus.FOUND, patientsRead.getStatusCode());

    }

    @Test
    public void testReadingAllPatientWithError() {
        //GIVEN
        Mockito.when(patientService.readingAllPatient()).thenReturn(Collections.emptyList());

        //WHEN
        ResponseEntity<Object> patientsRead = patientController.readingAllPatient();

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).readingAllPatient();
        Assertions.assertEquals("Erreur, aucuns patients enregistrés.",patientsRead.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, patientsRead.getStatusCode());

    }

    @Test
    public void testDeletingAllPatient() {
        //GIVEN
        Mockito.when(patientService.deletingAllPatient()).thenReturn(true);

        //WHEN
        ResponseEntity<String> patientsDeleted = patientController.deletingAllPatient();

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).deletingAllPatient();
        Assertions.assertEquals("Tous les patients ont été éffacés.",patientsDeleted.getBody());
        Assertions.assertEquals(HttpStatus.OK, patientsDeleted.getStatusCode());

    }

    @Test
    public void testDeletingAllPatientWithError() {
        //GIVEN
        Mockito.when(patientService.deletingAllPatient()).thenReturn(false);

        //WHEN
        ResponseEntity<String> patientsRead = patientController.deletingAllPatient();

        //THEN
        Mockito.verify(patientService,Mockito.times(1)).deletingAllPatient();
        Assertions.assertEquals("Erreur, aucuns patients à effacer.",patientsRead.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, patientsRead.getStatusCode());

    }
}
