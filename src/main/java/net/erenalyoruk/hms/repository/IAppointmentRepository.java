package net.erenalyoruk.hms.repository;

import java.sql.Timestamp;
import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.Doctor;

public interface IAppointmentRepository {
    List<Appointment> findAll();

    Appointment findOneById(int id);

    List<Appointment> findManyByPatient(Account account);

    List<Appointment> findManyByDoctor(Doctor doctor);

    List<Appointment> findManyByTimestamp(Timestamp timestamp);

    void insertOne(Appointment appointment);
}
