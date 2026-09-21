package com.example.patient.demo.controller;

import com.example.patient.demo.entity.Patient;
import com.example.patient.demo.service.PatientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // TEST
    @GetMapping("/test")
    public String test() {
        return "Patient Management System is working!";
    }

    // GET ALL PATIENTS
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // GET PATIENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(
            @PathVariable Long id) {

        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE PATIENT
    @PostMapping
    public Patient createPatient(
            @RequestBody Patient patient) {

        return patientService.createPatient(patient);
    }

    // UPDATE PATIENT
    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        return patientService.updatePatient(id, patient);
    }

    // DELETE PATIENT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        return ResponseEntity.ok("Patient deleted successfully");
    }
}