package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import net.erenalyoruk.hms.model.*;

import java.sql.Timestamp;
import java.util.List;

public class AppointmentRepository {
    private final EntityManager em;

    public AppointmentRepository(EntityManager em) {
        this.em = em;
    }

	public List<Appointment> findAll() {
		TypedQuery<Appointment> query = em.createQuery("FROM Appointment a", Appointment.class);
		return query.getResultList();
	}

	public Appointment findById(Long id) {
		return em.find(Appointment.class, id);
	}

	public Appointment findByPrescription(Prescription prescription) {
		TypedQuery<Appointment> query = em.createQuery("FROM Appointment a WHERE a.prescription = ?1", Appointment.class);
		query.setParameter(1, prescription);
		return query.getSingleResult();
	}

	public List<Appointment> findByDoctor(Doctor doctor) {
		TypedQuery<Appointment> query = em.createQuery("FROM Appointment a WHERE a.doctor = ?1", Appointment.class);
		query.setParameter(1, doctor);
		return query.getResultList();
	}

	public List<Appointment> findByPatient(Patient patient) {
		TypedQuery<Appointment> query = em.createQuery("FROM Appointment a WHERE a.patient = ?1", Appointment.class);
		query.setParameter(1, patient);
		return query.getResultList();
	}

	public List<Appointment> findByTimestamp(Timestamp timestamp) {
		TypedQuery<Appointment> query = em.createQuery("FROM Appointment a WHERE a.timestamp = ?1", Appointment.class);
		query.setParameter(1, timestamp);
		return query.getResultList();
	}

	public List<Appointment> findByStatus(AppointmentStatus status) {
		TypedQuery<Appointment> query = em.createQuery("FROM Appointment a WHERE a.status = ?1", Appointment.class);
		query.setParameter(1, status);
		return query.getResultList();
	}

	public void insertOne(Appointment appointment) {
		em.getTransaction().begin();
		em.persist(appointment);
		em.getTransaction().commit();
	}

	public void removeOne(Appointment appointment) {
		em.getTransaction().begin();
		em.remove(appointment);
		em.getTransaction().commit();
	}
}
