package net.erenalyoruk.hms.repository;

import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.Doctor;
import net.erenalyoruk.hms.model.Prescription;

public interface IPrescriptionRepository {
    List<Prescription> findAll();

    Prescription findOneById(int id);

    Prescription findOneByAppointment(Appointment appointment);

    List<Prescription> findManyByPatient(Account patient);

    List<Prescription> findManyByDoctor(Doctor doctor);

    void insertOne(Prescription prescription);
}
