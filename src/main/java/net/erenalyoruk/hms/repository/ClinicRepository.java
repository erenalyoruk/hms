package net.erenalyoruk.hms.repository;

import java.util.Set;
import net.erenalyoruk.hms.model.Clinic;

public interface ClinicRepository {

    Clinic findById(int id);

    Set<Clinic> findAll();

    void insertOne(Clinic clinic);
}
