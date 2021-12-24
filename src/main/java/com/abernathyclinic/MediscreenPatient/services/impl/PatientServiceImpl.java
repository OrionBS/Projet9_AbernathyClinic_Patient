package com.abernathyclinic.MediscreenPatient.services.impl;

import com.abernathyclinic.MediscreenPatient.models.Patient;
import com.abernathyclinic.MediscreenPatient.repositories.PatientRepository;
import com.abernathyclinic.MediscreenPatient.services.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient creatingPatient(Patient patient) {

        if (patientRepository.findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName()) != null) {
            return null;
        }
        if (patient.getFirstName() == null || patient.getLastName() == null || patient.getAddress() == null || patient.getGender() == null || patient.getDateOfBirth() == null || patient.getPhone() == null) {
            return null;
        }
        return patientRepository.saveAndFlush(patient);

    }

    @Override
    public Patient readingPatient(String firstName, String lastName) {

        if (firstName == null || lastName == null) {
            return null;
        }
        Patient patientAnswer = patientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (patientAnswer == null) {
            return null;
        }
        return patientAnswer;
    }

    @Override
    public Patient updatingPatient(Patient patient) {

        System.out.println(patient);

        if (patient.getId() == null || patient.getFirstName() == null || patient.getLastName() == null || patient.getAddress() == null || patient.getGender() == null || patient.getDateOfBirth() == null || patient.getPhone() == null) {
            System.out.println("Patient incomplets");
            return null;
        }

        return patientRepository.saveAndFlush(patient);
    }

    @Override
    public boolean deletingPatient(String firstName, String lastName) {

        if (firstName == null || lastName == null) {
            return false;
        }
        if (patientRepository.findByFirstNameAndLastName(firstName, lastName) == null) {
            return false;
        }
        patientRepository.deletePatientByFirstNameAndLastName(firstName, lastName);
        return true;
    }

    @Override
    public List<Patient> readingAllPatient() {

        if (patientRepository.findAll().isEmpty()) {
            return null;
        }
        return patientRepository.findAll();
    }

    @Override
    public boolean deletingAllPatient() {

        if (patientRepository.findAll().isEmpty()) {
            return false;
        }
        patientRepository.deleteAll();
        return true;
    }

}
