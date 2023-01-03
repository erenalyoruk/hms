package net.erenalyoruk.hms.repository;

import jakarta.persistence.*;
import java.util.List;
import net.erenalyoruk.hms.model.Account;

/** Layer between database and accounts. */
public class AccountRepository {
    private final EntityManager em;

    /**
     * Create repository.
     *
     * @param em Entity manager of Hibernate's persistence context.
     */
    public AccountRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * @return List of accounts that have doctor permission. If no account that have permission of
     *     doctor, empty list.
     */
    public List<Account> findDoctors() {
        TypedQuery<Account> query =
                em.createQuery(
                        "FROM Account account WHERE account.doctor IS NOT NULL", Account.class);
        return query.getResultList();
    }

    /**
     * Find account by citizen number.
     *
     * @param citizenNumber Unique ID of citizen that assigned by government.
     * @throws NoResultException If no results found, it throws.
     */
    public Account findOneByCitizenNumber(String citizenNumber) throws NoResultException {
        TypedQuery<Account> query =
                em.createQuery("FROM Account a WHERE a.citizenNumber = ?1", Account.class);
        query.setParameter(1, citizenNumber);
        return query.getSingleResult();
    }

    /**
     * Find account by email.
     *
     * @param email Email of account.
     * @throws NoResultException If no results found, it throws.
     */
    public Account findOneByEmail(String email) throws NoResultException {
        TypedQuery<Account> query =
                em.createQuery("FROM Account a WHERE a.email = ?1", Account.class);
        query.setParameter(1, email);
        return query.getSingleResult();
    }

    /**
     * Persist account to Hibernate's persistence context.
     *
     * @param account Account that will be persisted.
     * @throws EntityExistsException Throws if entity exists.
     */
    public void save(Account account) throws EntityExistsException {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    /**
     * Remove account from persistence context.
     *
     * @param account Account that will be removed from persistence context.
     * @throws IllegalArgumentException Throws if entity not exists.
     */
    public void remove(Account account) throws IllegalArgumentException {
        em.getTransaction().begin();
        em.remove(account);
        em.getTransaction().commit();
    }
}
