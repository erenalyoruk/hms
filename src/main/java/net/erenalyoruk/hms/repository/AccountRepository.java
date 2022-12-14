package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Gender;

public class AccountRepository implements IAccountRepository {
    private final EntityManager em;

    public AccountRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Account> findAll() {
        TypedQuery<Account> query = em.createQuery("FROM Account account", Account.class);
        return query.getResultList();
    }

    @Override
    public Account findOneById(int id) {
        return em.find(Account.class, id);
    }

    @Override
    public Account findOneByEmail(String email) {
        TypedQuery<Account> query = em.createQuery("FROM Account account WHERE account.email = ?1", Account.class);
        query.setParameter(1, email);
        return query.getSingleResult();
    }

    @Override
    public Account findOneBySecurityNumber(String securityNumber) {
        TypedQuery<Account> query = em.createQuery(
            "FROM Account account WHERE account.securityNumber = ?1",
            Account.class
        );
        query.setParameter(1, securityNumber);
        return query.getSingleResult();
    }

    @Override
    public List<Account> findManyByFirstName(String firstName) {
        TypedQuery<Account> query = em.createQuery("FROM Account account WHERE account.firstName = ?1", Account.class);
        query.setParameter(1, firstName);
        return query.getResultList();
    }

    @Override
    public List<Account> findManyByLastName(String lastName) {
        TypedQuery<Account> query = em.createQuery("FROM Account account WHERE account.lastName = ?1", Account.class);
        query.setParameter(1, lastName);
        return query.getResultList();
    }

    @Override
    public List<Account> findManyByAge(int age) {
        TypedQuery<Account> query = em.createQuery("FROM Account account WHERE account.age = ?1", Account.class);
        query.setParameter(1, age);
        return query.getResultList();
    }

    @Override
    public List<Account> findManyByGender(Gender gender) {
        TypedQuery<Account> query = em.createQuery("FROM Account account WHERE account.gender = ?1", Account.class);
        query.setParameter(1, gender);
        return query.getResultList();
    }

    @Override
    public void insertOne(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }
}
