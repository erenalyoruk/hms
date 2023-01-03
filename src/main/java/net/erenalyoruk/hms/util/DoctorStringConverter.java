package net.erenalyoruk.hms.util;

import java.util.List;
import javafx.util.StringConverter;
import net.erenalyoruk.hms.Main;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Doctor;

/** Utility class for converting Doctor class to string in JavaFX. */
public class DoctorStringConverter extends StringConverter<Doctor> {

    /**
     * Convert doctor object to string for JavaFX.
     *
     * @param doctor Doctor
     * @return Return the first name and last name of doctor seperated by space.
     */
    @Override
    public String toString(Doctor doctor) {
        if (doctor == null) {
            return "";
        }

        return doctor.getAccount().getFirstName() + " " + doctor.getAccount().getLastName();
    }

    /**
     * Find doctor with specified full name seperated by space.
     *
     * @param fullName Full name of doctor seperated by space.
     * @return Doctor if found, otherwise, null.
     */
    @Override
    public Doctor fromString(String fullName) {
        List<Account> accounts = Main.getAccountService().findAllDoctors();
        for (Account account : accounts) {
            if (account.getFirstName()
                    .concat(" " + account.getLastName())
                    .equalsIgnoreCase(fullName)) {
                return account.getDoctor();
            }
        }

        return null;
    }
}
