package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.*;

/** Layer between database and appointment. */
public class AppointmentRepository {
    private final EntityManager em;

    /**
     * Create repository.
     *
     * @param em Entity manager of Hibernate's persistence context.
     */
    public AppointmentRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * @return List of all appointments in database.
     */
    public List<Appointment> findAll() {
        TypedQuery<Appointment> query = em.createQuery("FROM Appointment a", Appointment.class);
        return query.getResultList();
    }

    /**
     * Persist appointment to Hibernate's persistence context.
     *
     * @param appointment Appointment that will be persisted.
     * @throws EntityExistsException Throw if entity exists.
     */
    public void save(Appointment appointment) throws EntityExistsException {
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
    }
}
