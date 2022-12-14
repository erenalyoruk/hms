package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.Account;

public class AccountRepository {
    private final EntityManager em;

    public AccountRepository(EntityManager em) {
        this.em = em;
    }

    public List<Account> findAll() {
        TypedQuery<Account> query = em.createQuery("FROM Account account", Account.class);
        return query.getResultList();
    }

    public Account findById(Long id) {
        return em.find(Account.class, id);
    }

    public Account findByCitizenNumber(String citizenNumber) {
        TypedQuery<Account> query = em.createQuery("FROM Account a WHERE a.citizenNumber = ?1", Account.class);
        query.setParameter(1, citizenNumber);
        return query.getSingleResult();
    }

    public Account findByEmail(String email) {
        TypedQuery<Account> query = em.createQuery("FROM Account a WHERE a.email = ?1", Account.class);
        query.setParameter(1, email);
        return query.getSingleResult();
    }

    public void insertOne(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    public void removeOne(Account account) {
        em.getTransaction().begin();
        em.remove(account);
        em.getTransaction().commit();
    }

    public void removeById(Long id) {
        removeOne(findById(id));
    }
}
