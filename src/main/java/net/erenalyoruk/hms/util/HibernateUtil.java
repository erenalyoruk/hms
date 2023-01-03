package net.erenalyoruk.hms.util;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import net.erenalyoruk.hms.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/** Utility class to manage Hibernate. */
@Getter
@Setter
public class HibernateUtil {
    private SessionFactory sessionFactory;
    private Session currentSession;

    private EntityManager entityManager;

    /** Create Hibernate context. */
    public HibernateUtil() {
        initializeHibernate();
    }

    /** Initialize Hibernate with all model classes. */
    private void initializeHibernate() {
        sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Account.class)
                        .addAnnotatedClass(Patient.class)
                        .addAnnotatedClass(Admin.class)
                        .addAnnotatedClass(Doctor.class)
                        .addAnnotatedClass(Appointment.class)
                        .addAnnotatedClass(Prescription.class)
                        .buildSessionFactory();

        currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        entityManager = currentSession.getEntityManagerFactory().createEntityManager();
        currentSession.getTransaction().commit();
    }
}
