package net.erenalyoruk.hms.util;

import jakarta.persistence.EntityManager;
import net.erenalyoruk.hms.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private SessionFactory sessionFactory;
    private Session currentSession;

    private EntityManager entityManager;

    public HibernateUtil() {
        initializeHibernate();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        if (currentSession == null) {
            initializeHibernate();
        }

        return currentSession;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close() {
        sessionFactory.close();
    }

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
