package net.erenalyoruk.hms.util;

import java.util.List;
import javafx.util.StringConverter;
import net.erenalyoruk.hms.Main;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Doctor;

public class DoctorStringConverter extends StringConverter<Doctor> {

    @Override
    public String toString(Doctor doctor) {
        if (doctor == null) {
            return "";
        }

        return doctor.getAccount().getFirstName() + " " + doctor.getAccount().getLastName();
    }

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
