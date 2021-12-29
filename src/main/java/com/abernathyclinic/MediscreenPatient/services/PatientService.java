package com.abernathyclinic.MediscreenPatient.services;

import com.abernathyclinic.MediscreenPatient.models.Patient;

import java.util.List;

public interface PatientService {

    /**
     * creating a patient on the repository.
     * @return the created patient.
     */
    public Patient creatingPatient(Patient patient) throws IllegalArgumentException;

    /**
     * reading a patient on the repository.
     * @return the read patient.
     */
    public Patient readingPatientByFirstNameAndLastName(String firstName, String lastName);

    /**
     * reading a patient on the repository.
     * @return the read patient.
     */
    public Patient readingPatientById(Integer patientId);

    /**
     * updating a patient on the repository.
     * @return the updated patient.
     */
    public Patient updatingPatient(Patient patient);

    /**
     * deleting a patient on the repository.
     */
    public boolean deletingPatient(String firstName, String lastName);

    /**
     * reading the entire list of patients on the repository.
     * @return the list of patients.
     */
    public List<Patient> readingAllPatient();

    /**
     * deleting the entire list of patients on the repository.
     */
    public boolean deletingAllPatient();
}
