package net.erenalyoruk.hms;

import jakarta.persistence.EntityManager;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Admin;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.Gender;
import net.erenalyoruk.hms.repository.*;
import net.erenalyoruk.hms.util.HibernateUtil;
import org.checkerframework.checker.units.qual.A;

public class Main {

    public static void main(String[] args) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        EntityManager entityManager = hibernateUtil.getEntityManager();
        AccountRepository accountRepository = new AccountRepository(entityManager);
        PatientRepository patientRepository = new PatientRepository(entityManager);
        DoctorRepository doctorRepository = new DoctorRepository(entityManager);
        PrescriptionRepository prescriptionRepository = new PrescriptionRepository(entityManager);
        AppointmentRepository appointmentRepository = new AppointmentRepository(entityManager);
        AdminRepository adminRepository = new AdminRepository(entityManager);

        Account account = new Account();
        account.setEmail("test@test.com");
        account.setCitizenNumber("19180283394");
        account.setGender(Gender.MALE);
        account.setPassword("pass123");
        account.setAge(21);

        Admin admin = new Admin();
        admin.setAccount(account);
        adminRepository.insertOne(admin);

        account.setAdmin(admin);
        accountRepository.insertOne(account);

        App.start(args);
    }
}
