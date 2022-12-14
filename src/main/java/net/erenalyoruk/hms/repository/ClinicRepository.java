package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.Clinic;
import net.erenalyoruk.hms.model.Doctor;

public class ClinicRepository implements IClinicRepository {
    private final EntityManager em;

    public ClinicRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Clinic> findAll() {
        TypedQuery<Clinic> query = em.createQuery("FROM Clinic clinic", Clinic.class);
        return query.getResultList();
    }

    @Override
    public Clinic findOneById(int id) {
        return em.find(Clinic.class, id);
    }

    @Override
    public List<Clinic> findManyByDoctor(Doctor doctor) {
        TypedQuery<Clinic> query = em.createQuery(
            "FROM Clinic clinic, IN (clinic.doctors) doctor WHERE doctor = ?1",
            Clinic.class
        );
        query.setParameter(1, doctor);
        return query.getResultList();
    }

    @Override
    public void insertOne(Clinic clinic) {
        em.getTransaction().begin();
        em.persist(clinic);
        em.getTransaction().commit();
    }
}
