package net.erenalyoruk.hms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import net.erenalyoruk.hms.model.Clinic;
import net.erenalyoruk.hms.model.Doctor;

public class DoctorRepository implements IDoctorRepository {
    private final EntityManager em;

    public DoctorRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Doctor findOneById(int id) {
        return em.find(Doctor.class, id);
    }

    @Override
    public Doctor findOneByClinic(Clinic clinic) {
        TypedQuery<Doctor> query = em.createQuery(
            "FROM Doctor doctor, IN (doctor.clinics) clinic WHERE clinic = ?1",
            Doctor.class
        );
        query.setParameter(1, clinic);
        return query.getSingleResult();
    }

    @Override
    public Doctor findOneByClinics(List<Clinic> clinics) {
        TypedQuery<Doctor> query = em.createQuery(
            "FROM Doctor doctor, IN (doctor.clinics) clinic WHERE clinic IN ?1",
            Doctor.class
        );
        query.setParameter(1, clinics);
        return query.getSingleResult();
    }

    @Override
    public List<Doctor> findManyByClinic(Clinic clinic) {
        TypedQuery<Doctor> query = em.createQuery(
            "FROM Doctor doctor, IN (doctor.clinics) clinic WHERE clinic = ?1",
            Doctor.class
        );
        query.setParameter(1, clinic);
        return query.getResultList();
    }

    @Override
    public List<Doctor> findManyByClinics(List<Clinic> clinics) {
        TypedQuery<Doctor> query = em.createQuery(
            "FROM Doctor doctor, IN (doctor.clinics) clinic WHERE clinic IN ?1",
            Doctor.class
        );
        query.setParameter(1, clinics);
        return query.getResultList();
    }

    @Override
    public void insertOne(Doctor doctor) {
        em.getTransaction().begin();
        em.persist(doctor);
        em.getTransaction().commit();
    }
}
