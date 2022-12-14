package net.erenalyoruk.hms;

import jakarta.persistence.EntityManager;
import java.sql.Timestamp;
import net.erenalyoruk.hms.model.*;
import net.erenalyoruk.hms.repository.*;
import net.erenalyoruk.hms.util.HashingUtil;
import net.erenalyoruk.hms.util.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        EntityManager entityManager = hibernateUtil.getEntityManager();

        AccountRepository accountRepository = new AccountRepository(entityManager);
        ClinicRepository clinicRepository = new ClinicRepository(entityManager);
        DoctorRepository doctorRepository = new DoctorRepository(entityManager);
        AppointmentRepository appointmentRepository = new AppointmentRepository(entityManager);
        PrescriptionRepository prescriptionRepository = new PrescriptionRepository(entityManager);

        Account account = new Account();
        account.setEmail("erenalyoruks@gmail.com");
        account.setFirstName("Eren");
        account.setLastName("Alyörük");
        account.setPassword(HashingUtil.sha256("test123"));
        account.setSecurityNumber("19180283394");
        account.setGender(Gender.MALE);
        account.setAge(21);

        accountRepository.insertOne(account);

        Clinic kbb = new Clinic("Kulak Burun Boğaz");
        Clinic goz = new Clinic("Göz");

        clinicRepository.insertOne(kbb);
        clinicRepository.insertOne(goz);

        Doctor doctor = new Doctor();
        doctor.setAccount(accountRepository.findOneBySecurityNumber("19180283394"));
        doctor.setClinics(clinicRepository.findAll());

        doctorRepository.insertOne(doctor);

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(account);
        appointment.setTimestamp(new Timestamp(System.currentTimeMillis()));

        appointmentRepository.insertOne(appointment);

        Prescription prescription = new Prescription(appointment, "ANTİBİYOTİK YAZDIM BUNA GO");

        prescriptionRepository.insertOne(prescription);

        System.out.println(
            prescriptionRepository
                .findOneByAppointment(appointmentRepository.findManyByPatient(account).get(0))
                .getInfo()
        );

        App.start(args);
    }
}
