package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.Doctor;
import net.erenalyoruk.hms.model.Prescription;

public class PrescriptionRepository implements IPrescriptionRepository {
    private final EntityManager em;

    public PrescriptionRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Prescription> findAll() {
        TypedQuery<Prescription> query = em.createQuery("FROM Prescription prescription", Prescription.class);
        return query.getResultList();
    }

    @Override
    public Prescription findOneById(int id) {
        return em.find(Prescription.class, id);
    }

    @Override
    public Prescription findOneByAppointment(Appointment appointment) {
        TypedQuery<Prescription> query = em.createQuery(
            "FROM Prescription prescription WHERE prescription.appointment = ?1",
            Prescription.class
        );
        query.setParameter(1, appointment);
        return query.getSingleResult();
    }

    @Override
    public List<Prescription> findManyByPatient(Account patient) {
        TypedQuery<Prescription> query = em.createQuery(
            "FROM Prescription prescription WHERE prescription.patient = ?1",
            Prescription.class
        );
        query.setParameter(1, patient);
        return query.getResultList();
    }

    @Override
    public List<Prescription> findManyByDoctor(Doctor doctor) {
        TypedQuery<Prescription> query = em.createQuery(
            "FROM Prescription prescription WHERE prescription.doctor = ?1",
            Prescription.class
        );
        query.setParameter(1, doctor);
        return query.getResultList();
    }

    @Override
    public void insertOne(Prescription prescription) {
        em.getTransaction().begin();
        em.persist(prescription);
        em.getTransaction().commit();
    }
}
