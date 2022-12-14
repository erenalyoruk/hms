package net.erenalyoruk.hms.repository;

import java.util.List;
import net.erenalyoruk.hms.model.Clinic;
import net.erenalyoruk.hms.model.Doctor;

public interface IDoctorRepository {
    Doctor findOneById(int id);

    Doctor findOneByClinic(Clinic clinic);

    Doctor findOneByClinics(List<Clinic> clinics);

    List<Doctor> findManyByClinic(Clinic clinic);

    List<Doctor> findManyByClinics(List<Clinic> clinics);

    void insertOne(Doctor doctor);
}
