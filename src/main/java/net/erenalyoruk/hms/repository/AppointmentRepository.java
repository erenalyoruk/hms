package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.Doctor;

public class AppointmentRepository implements IAppointmentRepository {
    private final EntityManager em;

    public AppointmentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Appointment> findAll() {
        TypedQuery<Appointment> query = em.createQuery("FROM Appointment appointment", Appointment.class);
        return query.getResultList();
    }

    @Override
    public Appointment findOneById(int id) {
        return em.find(Appointment.class, id);
    }

    @Override
    public List<Appointment> findManyByPatient(Account account) {
        TypedQuery<Appointment> query = em.createQuery(
            "FROM Appointment appointment WHERE appointment.patient = ?1",
            Appointment.class
        );
        query.setParameter(1, account);
        return query.getResultList();
    }

    @Override
    public List<Appointment> findManyByDoctor(Doctor doctor) {
        TypedQuery<Appointment> query = em.createQuery(
            "FROM Appointment appointment WHERE appointment.doctor = ?1",
            Appointment.class
        );
        query.setParameter(1, doctor);
        return query.getResultList();
    }

    @Override
    public List<Appointment> findManyByTimestamp(Timestamp timestamp) {
        TypedQuery<Appointment> query = em.createQuery(
            "FROM Appointment appointment WHERE appointment.timestamp = ?1",
            Appointment.class
        );
        query.setParameter(1, timestamp);
        return query.getResultList();
    }

    @Override
    public void insertOne(Appointment appointment) {
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
    }
}
