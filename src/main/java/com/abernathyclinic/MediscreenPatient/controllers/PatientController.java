package com.abernathyclinic.MediscreenPatient.controllers;

import com.abernathyclinic.MediscreenPatient.models.Patient;
import com.abernathyclinic.MediscreenPatient.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Object> creatingPatient(@RequestBody Patient patient) {
        Patient patientAnswer = patientService.creatingPatient(patient);

        if (patientAnswer == null) {
            return new ResponseEntity<>("Erreur, patient déjà existant ou informations incomplètes.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(patientAnswer, HttpStatus.CREATED);

    }

    @GetMapping(path = "/name")
    public ResponseEntity<Object> readingPatientByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        Patient patientAnswer = patientService.readingPatientByFirstNameAndLastName(firstName, lastName);

        if (patientAnswer == null) {
            return new ResponseEntity<>("Erreur, patient inexistant ou paramètres incorrects.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patientAnswer, HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<Object> readingPatientById(@RequestParam Integer patientId) {
        Patient patientAnswer = patientService.readingPatientById(patientId);

        if (patientAnswer == null) {
            return new ResponseEntity<>("Erreur, patient inexistant ou paramètres incorrects.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patientAnswer, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updatingPatient(@RequestBody Patient patient) {
        Patient patientAnswer = patientService.updatingPatient(patient);

        if (patientAnswer == null) {
            return new ResponseEntity<>("Erreur, informations incomplètes.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(patientAnswer, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletingPatient(String firstName, String lastName) {
        boolean patientAnswer = patientService.deletingPatient(firstName,lastName);

        if (!patientAnswer) {
            return new ResponseEntity<>("Erreur, patient inexistant ou paramètres incorrectes.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Patient correctement effacé.",HttpStatus.OK);
    }

    @GetMapping(path = "/debugAll")
    public ResponseEntity<Object> readingAllPatient() {
        List<Patient> patientListAnswer = patientService.readingAllPatient();

        if (patientListAnswer.isEmpty()) {
            return new ResponseEntity<>("Erreur, aucuns patients enregistrés.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patientListAnswer,HttpStatus.FOUND);
    }

    @DeleteMapping(path = "/debugAll")
    public ResponseEntity<String> deletingAllPatient() {
        boolean patientAnswer = patientService.deletingAllPatient();

        if (!patientAnswer) {
            return new ResponseEntity<>("Erreur, aucuns patients à effacer.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Tous les patients ont été éffacés.",HttpStatus.OK);
    }
}
