package com.example.patient.demo.controller;

import com.example.patient.demo.entity.Patient;
import com.example.patient.demo.service.AuditLogService;
import com.example.patient.demo.service.PatientService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;
    private final AuditLogService auditLogService;

    public PatientController(
            PatientService patientService,
            AuditLogService auditLogService) {

        this.patientService = patientService;
        this.auditLogService = auditLogService;
    }

    // TEST
    @GetMapping("/test")
    public String test() {
        return "Patient Management System is working!";
    }

    // GET ALL PATIENTS
    @GetMapping
    public List<Patient> getAllPatients() {

        List<Patient> patients = patientService.getAllPatients();

        auditLogService.log("VIEW_ALL", null);

        return patients;
    }

    // GET PATIENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(
            @PathVariable Long id) {

        Patient patient = patientService.getPatientById(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        auditLogService.log("VIEW", id);

        return ResponseEntity.ok(patient);
    }

    // CREATE PATIENT
    @PostMapping
    public Patient createPatient(
            @Valid @RequestBody Patient patient) {

        Patient savedPatient =
                patientService.createPatient(patient);

        auditLogService.log(
                "CREATE",
                savedPatient.getId()
        );

        return savedPatient;
    }

    // UPDATE PATIENT
    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody Patient patient) {

        Patient updatedPatient =
                patientService.updatePatient(id, patient);

        auditLogService.log(
                "UPDATE",
                id
        );

        return updatedPatient;
    }

    // DELETE PATIENT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        auditLogService.log(
                "DELETE",
                id
        );

        return ResponseEntity.ok(
                "Patient deleted successfully"
        );
    }
}