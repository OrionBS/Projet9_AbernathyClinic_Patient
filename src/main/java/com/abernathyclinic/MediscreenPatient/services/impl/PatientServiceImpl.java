package com.abernathyclinic.MediscreenPatient.services.impl;

import com.abernathyclinic.MediscreenPatient.models.Patient;
import com.abernathyclinic.MediscreenPatient.repositories.PatientRepository;
import com.abernathyclinic.MediscreenPatient.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient creatingPatient(Patient patient) {

        logger.debug("Ajout du patient, " + patient.toString());

        if (patientRepository.findByFirstNameAndLastName(patient.getFirstName(), patient.getLastName()) != null) {
            logger.error("Error, patient déjà présent.");
            return null;
        }
        if (patient.getFirstName() == null || patient.getLastName() == null || patient.getGender() == null || patient.getDateOfBirth() == null) {
            logger.error("Error, des informations sur le patients sont manquantes : " + patient);
            return null;
        }
        return patientRepository.saveAndFlush(patient);

    }

    @Override
    public Patient readingPatientByFirstNameAndLastName(String firstName, String lastName) {

        logger.debug("Lecture du patient :" + firstName + " " + lastName);

        if (firstName == null || lastName == null) {
            logger.error("Error, prénom ou nom nul.");
            return null;
        }
        Patient patientAnswer = patientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (patientAnswer == null) {
            logger.error("Error, patient introuvable.");
            return null;
        }
        return patientAnswer;
    }

    @Override
    public Patient readingPatientById(Integer patientId) {

        logger.debug("Lecture du patient :" + patientId);

        if (patientId == null) {
            logger.error("Error, patientId nul.");
            return null;
        }

        Patient patientAnswer;

        try {
             patientAnswer = patientRepository.findById(patientId).get();
        } catch (NoSuchElementException e) {
            logger.error("Error, patient introuvable.");
            return null;
        }

        return patientAnswer;
    }

    @Override
    public Patient updatingPatient(Patient patient) {

        logger.debug("Mise à jour du patient : " + patient);

        System.out.println(patient);

        if (patient.getId() == null || patient.getFirstName() == null || patient.getLastName() == null || patient.getGender() == null || patient.getDateOfBirth() == null) {
            logger.error("Error, des informations sur le patients sont manquantes : " + patient);
            return null;
        }

        return patientRepository.saveAndFlush(patient);
    }

    @Override
    public boolean deletingPatient(String firstName, String lastName) {

        logger.debug("Suppression du patient : " + firstName + " " + lastName);

        if (firstName == null || lastName == null) {
            logger.error("Error, prénom ou nom nul.");
            return false;
        }
        if (patientRepository.findByFirstNameAndLastName(firstName, lastName) == null) {
            logger.error("Error, patient introuvable.");
            return false;
        }
        patientRepository.deletePatientByFirstNameAndLastName(firstName, lastName);
        return true;
    }

    @Override
    public List<Patient> readingAllPatient() {

        logger.debug("Lecture du tous les patients.");

        List<Patient> patients = patientRepository.findAll();

        if (patients.isEmpty()) {
            return null;
        }
        return patients;
    }

    @Override
    public boolean deletingAllPatient() {

        logger.debug("Suppression de tous les patients.");

        List<Patient> patients = patientRepository.findAll();

        if (patients.isEmpty()) {
            return false;
        }

        patientRepository.deleteAll();
        return true;
    }

}
