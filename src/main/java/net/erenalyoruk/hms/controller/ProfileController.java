package net.erenalyoruk.hms.controller;

import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.provider.SessionProvider;

public class ProfileController {

    @FXML private Text citizenNumberText;

    @FXML private Text firstNameText;

    @FXML private Text lastNameText;

    @FXML private Text emailText;

    @FXML private Text genderText;

    @FXML private Text birthDateText;

    @FXML
    private void initialize() {
        Account account = SessionProvider.getAccount();
        citizenNumberText.setText(account.getCitizenNumber());
        firstNameText.setText(account.getFirstName());
        lastNameText.setText(account.getLastName());
        emailText.setText(account.getEmail());
        genderText.setText(account.getGender().toString());
        LocalDateTime date = account.getDateOfBirth().toLocalDateTime();
        birthDateText.setText(
                date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear());
    }
}
