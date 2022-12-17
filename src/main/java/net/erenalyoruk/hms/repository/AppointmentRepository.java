package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;
import net.erenalyoruk.hms.model.*;

public class AppointmentRepository {
    private final EntityManager em;

    public AppointmentRepository(EntityManager em) {
        this.em = em;
    }

    public List<Appointment> findAll() {
        TypedQuery<Appointment> query = em.createQuery("FROM Appointment a", Appointment.class);
        return query.getResultList();
    }

    public List<Appointment> findManyByDoctor(Doctor doctor) {
        TypedQuery<Appointment> query = em.createQuery("FROM Appointment a WHERE a.doctor = ?1", Appointment.class);
        query.setParameter(1, doctor);
        return query.getResultList();
    }

    public List<Appointment> findManyByPatient(Patient patient) {
        TypedQuery<Appointment> query = em.createQuery("FROM Appointment a WHERE a.patient = ?1", Appointment.class);
        query.setParameter(1, patient);
        return query.getResultList();
    }

    public List<Appointment> findManyByTimestamp(Timestamp timestamp) {
        TypedQuery<Appointment> query = em.createQuery("FROM Appointment a WHERE a.timestamp = ?1", Appointment.class);
        query.setParameter(1, timestamp);
        return query.getResultList();
    }

    public List<Appointment> findManyByStatus(AppointmentStatus status) {
        TypedQuery<Appointment> query = em.createQuery("FROM Appointment a WHERE a.status = ?1", Appointment.class);
        query.setParameter(1, status);
        return query.getResultList();
    }

    public void save(Appointment appointment) throws EntityExistsException {
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
    }
}
