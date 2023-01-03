package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import net.erenalyoruk.hms.model.Prescription;

/** Layer between prescription and database. */
public class PrescriptionRepository {
    private final EntityManager em;

    /**
     * Create repository.
     *
     * @param em Entity manager of Hibernate's persistence context.
     */
    public PrescriptionRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * Persist prescription to persistence context.
     *
     * @param prescription Prescription that will be persisted.
     * @throws EntityExistsException Throws if entity exists.
     */
    public void save(Prescription prescription) throws EntityExistsException {
        em.getTransaction().begin();
        em.persist(prescription);
        em.getTransaction().commit();
    }
}
