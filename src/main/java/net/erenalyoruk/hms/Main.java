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
    private static AccountService accountService;
    private static AppointmentService appointmentService;
    private static PrescriptionService prescriptionService;

    public static void main(String[] args) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        EntityManager entityManager = hibernateUtil.getEntityManager();

        AccountRepository accountRepository = new AccountRepository(entityManager);
        PrescriptionRepository prescriptionRepository = new PrescriptionRepository(entityManager);
        AppointmentRepository appointmentRepository = new AppointmentRepository(entityManager);

        accountService = new AccountService(accountRepository);
        appointmentService = new AppointmentService(appointmentRepository);
        prescriptionService = new PrescriptionService(prescriptionRepository);

        Account account = new Account();
        account.setGender(Gender.MALE);
        account.setEmail("erenalyoruks@gmail.com");
        account.setCitizenNumber("123");
        account.setPassword("test123");
        account.setDateOfBirth(new Timestamp(System.currentTimeMillis()));
        account.makeDoctor();
        account.setFirstName("Eren");
        account.setLastName("Alyörük");
        accountService.createAccount(account);

        appointmentService.createAppointment(
                account.getPatient(),
                account.getDoctor(),
                new Timestamp(System.currentTimeMillis()));

        prescriptionService.createPrescription(
                appointmentService.getAppointments(account.getPatient()).get(0), "test");

        System.out.println(accountService.canLogin("erenalyoruks@gmail.com", "test1s23"));

        accountService.removeAccount(account);

        App.start(args);
    }

    public static AccountService getAccountService() {
        return accountService;
    }

    public static AppointmentService getAppointmentService() {
        return appointmentService;
    }

    public static PrescriptionService getPrescriptionService() {
        return prescriptionService;
    }
}
