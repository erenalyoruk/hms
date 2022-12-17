package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.*;

public class PatientRepository {
    private final EntityManager em;

    public PatientRepository(EntityManager em) {
        this.em = em;
    }

    public List<Patient> findAll() {
        TypedQuery<Patient> query = em.createQuery("FROM Patient patient", Patient.class);
        return query.getResultList();
    }

    public Patient findOneById(Long id) {
        return em.find(Patient.class, id);
    }

    public Patient findOneByAccount(Account account) {
        return findOneById(account.getId());
    }

    public Patient findOneByAppointment(Appointment appointment) {
        TypedQuery<Patient> query = em.createQuery(
            "FROM Patient patient, IN (patient.appointments) a WHERE a = ?1",
            Patient.class
        );
        query.setParameter(1, appointment);
        return query.getSingleResult();
    }

    public Patient findOneByPrescription(Prescription prescription) {
        TypedQuery<Patient> query = em.createQuery(
            "FROM Patient patient, IN (patient.prescriptions) p WHERE p = ?1",
            Patient.class
        );
        query.setParameter(1, prescription);
        return query.getSingleResult();
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
