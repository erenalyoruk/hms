package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Admin;

public class AdminRepository {
    private final EntityManager em;

    public AdminRepository(EntityManager em) {
        this.em = em;
    }

    public List<Admin> findAll() {
        TypedQuery<Admin> query = em.createQuery("FROM Admin admin", Admin.class);
        return query.getResultList();
    }

    public Admin findOneById(Long id) {
        return em.find(Admin.class, id);
    }

    public Admin findOneByAccount(Account account) {
        return findOneById(account.getId());
    }
}
