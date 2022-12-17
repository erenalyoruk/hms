package net.erenalyoruk.hms.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.Doctor;
import net.erenalyoruk.hms.model.Patient;
import net.erenalyoruk.hms.model.Prescription;
import net.erenalyoruk.hms.repository.PrescriptionRepository;

public class PrescriptionService {
    private final PrescriptionRepository repository;

    public PrescriptionService(PrescriptionRepository repository) {
        this.repository = repository;
    }

    public List<Prescription> getPrescriptions() {
        return repository.findAll();
    }

    public List<Prescription> getPrescriptions(Patient patient) {
        return repository.findManyByPatient(patient);
    }

    public List<Prescription> getPrescriptions(Doctor doctor) {
        return repository.findManyByDoctor(doctor);
    }

    public Prescription findPrescription(Appointment appointment) {
        return repository.findOneByAppointment(appointment);
    }

    public boolean createPrescription(Appointment appointment, String details) {
        try {
            appointment.createPrescription(details);
            repository.save(appointment.getPrescription());
            return true;
        } catch (EntityExistsException exception) {
            return false;
        }
    }

    public boolean removePrescription(Prescription prescription) {
        try {
            prescription.getAppointment().removePrescription();
            repository.remove(prescription);
            return true;
        } catch (EntityNotFoundException exception) {
            return false;
        }
    }
}
