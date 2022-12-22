package net.erenalyoruk.hms.service;

import jakarta.persistence.EntityExistsException;
import java.sql.Timestamp;
import java.util.List;
import net.erenalyoruk.hms.model.*;
import net.erenalyoruk.hms.repository.AppointmentRepository;

public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> getAppointments() {
        return repository.findAll();
    }

    public List<Appointment> getAppointments(AppointmentStatus status) {
        return repository.findManyByStatus(status);
    }

    public List<Appointment> getAppointments(Timestamp timestamp) {
        return repository.findManyByTimestamp(timestamp);
    }

    public List<Appointment> getAppointments(Patient patient) {
        return repository.findManyByPatient(patient);
    }

    public List<Appointment> getAppointments(Doctor doctor) {
        return repository.findManyByDoctor(doctor);
    }

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

    public void setStatus(Appointment appointment, AppointmentStatus status) {
        appointment.setStatus(status);
        repository.save(appointment);
    }

    public boolean removeAppointment(Appointment appointment) {
        Doctor doctor = appointment.getDoctor();
        Patient patient = appointment.getPatient();

        try {
            doctor.removeAppointment(appointment);
            patient.removeAppointment(appointment);

            repository.save(appointment);
            return true;
        } catch (EntityExistsException exception) {
            return false;
        }
    }
}
