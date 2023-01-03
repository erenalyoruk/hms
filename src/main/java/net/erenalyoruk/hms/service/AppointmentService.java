package net.erenalyoruk.hms.service;

import jakarta.persistence.EntityExistsException;
import java.sql.Timestamp;
import java.util.List;
import net.erenalyoruk.hms.model.*;
import net.erenalyoruk.hms.repository.AppointmentRepository;

/** Layer between repository and application. */
public class AppointmentService {
    private final AppointmentRepository repository;

    /**
     * Create service for repository.
     *
     * @param repository AppointmentRepository
     */
    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Find all appointments.
     *
     * @return List of all appointments.
     */
    public List<Appointment> getAppointments() {
        return repository.findAll();
    }

    /**
     * Create appointment and save it.
     *
     * @param patient Patient who scheduled appointment.
     * @param doctor Doctor that selected by patient.
     * @param timestamp Timestamp of appointment.
     * @return Appointment if operation successed. Otherwise, null.
     */
    public Appointment createAppointment(Patient patient, Doctor doctor, Timestamp timestamp) {
        try {
            Appointment appointment = new Appointment();
            appointment.setTimestamp(timestamp);
            patient.addAppointment(appointment);
            doctor.addAppointment(appointment);
            repository.save(appointment);
            return appointment;
        } catch (EntityExistsException exception) {
            return null;
        }
    }

    /**
     * Set status of appointment.
     *
     * @param appointment Appointment
     * @param status The status of appointment that will be set.
     */
    public void setStatus(Appointment appointment, AppointmentStatus status) {
        appointment.setStatus(status);
        repository.save(appointment);
    }
}
