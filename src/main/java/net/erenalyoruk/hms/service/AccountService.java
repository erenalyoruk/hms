package net.erenalyoruk.hms.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.repository.AccountRepository;

public class AccountService implements AccountRepository {

    private final EntityManager em;

    public AccountService(EntityManager em) {
        this.em = em;
    }

    @Override
    public Account findById(int id) {
        return em.find(Account.class, id);
    }

    @Override
    public Account findByEmail(String email) {
        Query query =
                em.createQuery("SELECT Account FROM Account account WHERE account.email = ?1");
        query.setParameter(1, email);
        return (Account) query.getSingleResult();
    }

    @Override
    public Account findBySecurityNumber(String securityNumber) {
        Query query =
                em.createQuery(
                        "SELECT Account FROM Account account WHERE account.securityNumber = ?1");
        query.setParameter(1, securityNumber);
        return (Account) query.getSingleResult();
    }

    @Override
    public void insertOne(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }
}
