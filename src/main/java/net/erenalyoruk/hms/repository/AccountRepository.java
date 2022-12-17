package net.erenalyoruk.hms.repository;

import jakarta.persistence.*;
import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Doctor;
import net.erenalyoruk.hms.model.Patient;

public class AccountRepository {
    private final EntityManager em;

    public AccountRepository(EntityManager em) {
        this.em = em;
    }

    public List<Account> findAll() {
        TypedQuery<Account> query = em.createQuery("FROM Account account", Account.class);
        return query.getResultList();
    }

    public Account findOneById(Long id) {
        return em.find(Account.class, id);
    }

    public Account findOneByPatient(Patient patient) {
        return findOneById(patient.getId());
    }

    public Account findOneByDoctor(Doctor doctor) {
        return findOneById(doctor.getId());
    }

    public Account findOneByCitizenNumber(String citizenNumber) throws NoResultException {
        TypedQuery<Account> query = em.createQuery("FROM Account a WHERE a.citizenNumber = ?1", Account.class);
        query.setParameter(1, citizenNumber);
        return query.getSingleResult();
    }

    public Account findOneByEmail(String email) throws NoResultException {
        TypedQuery<Account> query = em.createQuery("FROM Account a WHERE a.email = ?1", Account.class);
        query.setParameter(1, email);
        return query.getSingleResult();
    }

    public void save(Account account) throws EntityExistsException {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    public void removeOne(Account account) throws IllegalArgumentException {
        em.getTransaction().begin();
        em.remove(account);
        em.getTransaction().commit();
    }

    public void removeById(Long id) {
        removeOne(findOneById(id));
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
