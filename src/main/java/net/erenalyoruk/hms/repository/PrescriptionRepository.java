package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.Doctor;
import net.erenalyoruk.hms.model.Patient;
import net.erenalyoruk.hms.model.Prescription;

public class PrescriptionRepository {
    private final EntityManager em;

    public PrescriptionRepository(EntityManager em) {
        this.em = em;
    }

    public List<Prescription> findAll() {
        TypedQuery<Prescription> query = em.createQuery("FROM Prescription prescription", Prescription.class);
        return query.getResultList();
    }

    public Prescription findOneById(Long id) {
        return em.find(Prescription.class, id);
    }

    public Prescription findOneByAppointment(Appointment appointment) {
        TypedQuery<Prescription> query = em.createQuery(
            "FROM Prescription p WHERE p.appointment = ?1",
            Prescription.class
        );
        query.setParameter(1, appointment);
        return query.getSingleResult();
    }

    public List<Prescription> findManyByPatient(Patient patient) {
        TypedQuery<Prescription> query = em.createQuery("FROM Prescription p WHERE p.patient = ?1", Prescription.class);
        query.setParameter(1, patient);
        return query.getResultList();
    }

    public List<Prescription> findManyByDoctor(Doctor doctor) {
        TypedQuery<Prescription> query = em.createQuery("FROM Prescription p WHERE p.doctor = ?1", Prescription.class);
        query.setParameter(1, doctor);
        return query.getResultList();
    }

    public void save(Prescription prescription) throws EntityExistsException {
        em.getTransaction().begin();
        em.persist(prescription);
        em.getTransaction().commit();
    }

    public void remove(Prescription prescription) throws EntityNotFoundException {
        em.getTransaction().begin();
        em.remove(prescription);
        em.getTransaction().commit();
    }
}
