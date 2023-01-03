package net.erenalyoruk.hms.service;

import jakarta.persistence.EntityExistsException;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.repository.PrescriptionRepository;

/** Layer between repository and application. */
public class PrescriptionService {
    private final PrescriptionRepository repository;

    /**
     * Create service for repository.
     *
     * @param repository PrescriptionRepository
     */
    public PrescriptionService(PrescriptionRepository repository) {
        this.repository = repository;
    }

    /**
     * Create prescription for appointment with details.
     *
     * @param appointment Appointment
     * @param details Details of prescription.
     */
    public void createPrescription(Appointment appointment, String details) {
        try {
            appointment.createPrescription(details);
            repository.save(appointment.getPrescription());
        } catch (EntityExistsException exception) {
            return;
        }
    }
}
