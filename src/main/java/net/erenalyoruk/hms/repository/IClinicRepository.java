package net.erenalyoruk.hms.repository;

import java.util.List;
import net.erenalyoruk.hms.model.Clinic;
import net.erenalyoruk.hms.model.Doctor;

public interface IClinicRepository {
    /**
     * Finds all clinics.
     *
     * @return List of clinics.
     */
    List<Clinic> findAll();

    /**
     * Finds clinic by id.
     *
     * @param id Unique id of clinic.
     * @return Clinic.
     */
    Clinic findOneById(int id);

    /**
     * Find clinics by doctor.
     *
     * @param doctor Clinic doctor.
     * @return Clinic list of a doctor.
     */
    List<Clinic> findManyByDoctor(Doctor doctor);

    /**
     * Add new clinic to database.
     *
     * @param clinic Clinic.
     */
    void insertOne(Clinic clinic);
}
