package com.abernathyclinic.MediscreenPatient.units;

import com.abernathyclinic.MediscreenPatient.models.Patient;
import com.abernathyclinic.MediscreenPatient.repositories.PatientRepository;
import com.abernathyclinic.MediscreenPatient.services.PatientService;
import com.abernathyclinic.MediscreenPatient.services.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void testCreatingPatient() {
        //GIVEN
        Patient patient = new Patient("testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientRepository.findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName())).thenReturn(null);
        Mockito.when(patientRepository.saveAndFlush(patient)).thenReturn(patient);

        //WHEN
        Patient patientSave = patientService.creatingPatient(patient);

        //THEN
        Mockito.verify(patientRepository, Mockito.times(1)).findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName());
        Mockito.verify(patientRepository, Mockito.times(1)).saveAndFlush(patient);
        Assertions.assertEquals(patient,patientSave);

    }

    @Test
    public void testReadingPatientByFirstNameAndLastName() {
        //GIVEN
        Patient patient = new Patient("testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientRepository.findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName())).thenReturn(patient);

        //WHEN
        Patient patientRead = patientService.readingPatientByFirstNameAndLastName(patient.getFirstName(),patient.getLastName());

        //THEN
        Mockito.verify(patientRepository, Mockito.times(1)).findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName());
        Assertions.assertEquals(patient,patientRead);

    }

    @Test
    public void testReadingPatientById() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        //WHEN
        Patient patientRead = patientService.readingPatientById(patient.getId());

        //THEN
        Mockito.verify(patientRepository, Mockito.times(1)).findById(patient.getId());
        Assertions.assertEquals(patient,patientRead);

    }

    @Test
    public void testUpdatingPatient() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientRepository.saveAndFlush(patient)).thenReturn(patient);

        //WHEN
        Patient patientUpdate = patientService.updatingPatient(patient);

        //THEN
        Mockito.verify(patientRepository, Mockito.times(1)).saveAndFlush(patient);
        Assertions.assertEquals(patient,patientUpdate);

    }

    @Test
    public void testDeletingPatient() {
        //GIVEN
        Patient patient = new Patient(12,"testFirstName", "testLastName", LocalDate.now(), "M", "Somewhere", "Some phone");
        Mockito.when(patientRepository.findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName())).thenReturn(patient);


        //WHEN
        boolean patientDelete = patientService.deletingPatient(patient.getFirstName(),patient.getLastName());


        //THEN
        Mockito.verify(patientRepository, Mockito.times(1)).findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName());
        Mockito.verify(patientRepository, Mockito.times(1)).deletePatientByFirstNameAndLastName(patient.getFirstName(), patient.getLastName());
        Assertions.assertTrue(patientDelete);

    }

    @Test
    public void testReadingAllPatient() {
        //GIVEN
        List<Patient> patients = Collections.singletonList(new Patient());
        Mockito.when(patientRepository.findAll()).thenReturn(patients);

        //WHEN
        List<Patient> patientsRead = patientService.readingAllPatient();

        //THEN
        Mockito.verify(patientRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(patients,patientsRead);

    }

    @Test
    public void testDeletingAllPatient() {
        //GIVEN
        List<Patient> patients = Collections.singletonList(new Patient());
        Mockito.when(patientRepository.findAll()).thenReturn(patients);

        //WHEN
        boolean patientsDelete = patientService.deletingAllPatient();

        //THEN
        Mockito.verify(patientRepository, Mockito.times(1)).findAll();
        Mockito.verify(patientRepository, Mockito.times(1)).deleteAll();
        Assertions.assertTrue(patientsDelete);

    }

}
