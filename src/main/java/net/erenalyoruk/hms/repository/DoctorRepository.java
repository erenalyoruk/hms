package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.Doctor;
import net.erenalyoruk.hms.model.Prescription;

public class DoctorRepository {
    private final EntityManager em;

    public DoctorRepository(EntityManager em) {
        this.em = em;
    }

    public List<Doctor> findAll() {
        TypedQuery<Doctor> query = em.createQuery("FROM Doctor doctor", Doctor.class);
        return query.getResultList();
    }

    public Doctor findOneById(Long id) {
        return em.find(Doctor.class, id);
    }

    public Doctor findOneByAccount(Account account) {
        return findOneById(account.getId());
    }

    public Doctor findOneByAppointment(Appointment appointment) {
        TypedQuery<Doctor> query = em.createQuery(
            "FROM Doctor doctor, IN (doctor.appointments) a WHERE a = ?1",
            Doctor.class
        );
        query.setParameter(1, appointment);
        return query.getSingleResult();
    }

    public Doctor findOneByPrescription(Prescription prescription) {
        TypedQuery<Doctor> query = em.createQuery(
            "FROM Doctor doctor, IN (doctor.prescriptions) p WHERE p = ?1",
            Doctor.class
        );
        query.setParameter(1, prescription);
        return query.getSingleResult();
    }
}
