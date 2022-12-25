package net.erenalyoruk.hms.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.erenalyoruk.hms.Main;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Gender;

public class RegisterController {

    @FXML private TextField citizenNumberField;

    @FXML private TextField emailField;

    @FXML private PasswordField passwordField;

    @FXML private TextField firstNameField;

    @FXML private TextField lastNameField;

    @FXML private ChoiceBox<Gender> genderChoice;

    @FXML private DatePicker birthDate;

    @FXML private Text registerText;

    @FXML
    private void initialize() {
        ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.values());
        registerText.setVisible(false);
        genderChoice.setItems(genders);
    }

    @FXML
    private void register() {
        if (citizenNumberField.getText().isBlank()
                || emailField.getText().isBlank()
                || passwordField.getText().isBlank()
                || firstNameField.getText().isBlank()
                || lastNameField.getText().isBlank()
                || genderChoice.getValue() == null
                || birthDate.getValue() == null) {
            return;
        }

        if (citizenNumberField.getText().length() != 11
                || !citizenNumberField.getText().matches("[0-9]+")) {
            registerText.setText("Please enter valid citizen number.");
            registerText.setVisible(true);
            return;
        }

        if (!emailField.getText().matches("^(.+)@(.+)$")) {
            registerText.setText("Please enter valid email.");
            registerText.setVisible(true);
            return;
        }

        if (birthDate.getValue().isAfter(LocalDate.from(ZonedDateTime.now()))) {
            return;
        }

        if (!Main.getAccountService().isCitizenNumberUnique(citizenNumberField.getText())) {
            registerText.setText("Citizen number must be unique.");
            registerText.setVisible(true);
            return;
        }

        if (!Main.getAccountService().isEmailUnique(emailField.getText())) {
            registerText.setText("Email must be unique.");
            registerText.setVisible(true);
            return;
        }

        Account account = new Account();
        account.setCitizenNumber(citizenNumberField.getText());
        account.setEmail(emailField.getText());
        account.setPassword(passwordField.getText());
        account.setFirstName(firstNameField.getText());
        account.setLastName(lastNameField.getText());
        account.setGender(genderChoice.getValue());

        Calendar combinedCalendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Istanbul"));
        combinedCalendar.clear();
        LocalDate date = birthDate.getValue();
        combinedCalendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        account.setDateOfBirth(new Timestamp(combinedCalendar.getTimeInMillis()));

        if (!Main.getAccountService().createAccount(account)) {
            registerText.setText("Something went wrong.");
            registerText.setVisible(true);
            return;
        }

        registerText.setFill(Color.GREEN);
        registerText.setText("Registration successful. Please log in to your account.");
        registerText.setVisible(true);
    }
}
