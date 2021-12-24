package com.abernathyclinic.MediscreenPatient.repositories;

import com.abernathyclinic.MediscreenPatient.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    /**
     * find on the mongoDB a patient in terms of firstName and lastName;
     *
     * @param firstName of the patient.
     * @param lastName  of the patient.
     * @return the patient found.
     */
    Patient findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * delete on the mongoDB a patient in terms of firstName and lastName;
     *
     * @param firstName of the patient.
     * @param lastName  of the patient.
     */
    void deletePatientByFirstNameAndLastName(String firstName, String lastName);
}
