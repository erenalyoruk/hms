package net.erenalyoruk.hms;

import jakarta.persistence.EntityManager;
import java.sql.Timestamp;
import net.erenalyoruk.hms.model.*;
import net.erenalyoruk.hms.repository.*;
import net.erenalyoruk.hms.service.AccountService;
import net.erenalyoruk.hms.service.AppointmentService;
import net.erenalyoruk.hms.service.PrescriptionService;
import net.erenalyoruk.hms.util.HibernateUtil;

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

        AccountService accountService = new AccountService(accountRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        PrescriptionService prescriptionService = new PrescriptionService(prescriptionRepository);

        Account account = new Account();
        account.setGender(Gender.MALE);
        account.setEmail("erenalyoruks@gmail.com");
        account.setCitizenNumber("123");
        account.setPassword("test123");
        account.setDateOfBirth(new Timestamp(System.currentTimeMillis()));
        account.makeDoctor();
        accountService.createAccount(account);

        appointmentService.createAppointment(
            account.getPatient(),
            account.getDoctor(),
            new Timestamp(System.currentTimeMillis())
        );

        prescriptionService.createPrescription(appointmentService.getAppointments(account.getPatient()).get(0), "test");

        prescriptionService.removePrescription(prescriptionRepository.findOneById(1L));

        System.out.println(accountService.canLogin("erenalyoruks@gmail.com", "test1s23"));

        App.start(args);
    }
}
