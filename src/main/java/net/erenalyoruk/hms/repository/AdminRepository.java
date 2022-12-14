package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
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

    public Admin findById(Long id) {
        return em.find(Admin.class, id);
    }

    public void insertOne(Admin admin) {
        em.getTransaction().begin();
        em.persist(admin);
        em.getTransaction().commit();
    }

    public void removeOne(Admin admin) {
        em.getTransaction().begin();
        em.remove(admin);
        em.getTransaction().commit();
    }

    public void removeOneById(Long id) {
        removeOne(findById(id));
    }
}
