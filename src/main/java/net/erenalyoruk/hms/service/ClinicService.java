package net.erenalyoruk.hms.service;

import jakarta.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;
import net.erenalyoruk.hms.model.Clinic;
import net.erenalyoruk.hms.repository.ClinicRepository;

public class ClinicService implements ClinicRepository {

    private EntityManager em;

    public ClinicService(EntityManager em) {
        this.em = em;
    }

    @Override
    public Clinic findById(int id) {
        return em.find(Clinic.class, id);
    }

    @Override
    public Set<Clinic> findAll() {
        return new HashSet<>(em.createQuery("SELECT Clinic FROM Clinic c").getResultList());
    }

    @Override
    public void insertOne(Clinic clinic) {
        em.getTransaction().begin();
        em.persist(clinic);
        em.getTransaction().commit();
    }
}
