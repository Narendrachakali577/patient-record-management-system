package com.example.patient.demo.service;

import com.example.patient.demo.entity.Patient;
import com.example.patient.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // CREATE
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // GET ALL
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // GET BY ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // UPDATE
    public Patient updatePatient(Long id, Patient patient) {

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        existingPatient.setName(patient.getName());
        existingPatient.setAge(patient.getAge());
        existingPatient.setGender(patient.getGender());
        existingPatient.setDisease(patient.getDisease());

        return patientRepository.save(existingPatient);
    }

    // DELETE
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}