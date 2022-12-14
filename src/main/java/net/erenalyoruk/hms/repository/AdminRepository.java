package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.Admin;

public class AdminRepository implements IAdminRepository {
    private final EntityManager em;

    public AdminRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Admin> findAll() {
        TypedQuery<Admin> query = em.createQuery("FROM Admin admin", Admin.class);
        return query.getResultList();
    }

    @Override
    public Admin findOneById(int id) {
        return em.find(Admin.class, id);
    }

    @Override
    public void insertOne(Admin admin) {
        em.getTransaction().begin();
        em.persist(admin);
        em.getTransaction().commit();
    }
}
