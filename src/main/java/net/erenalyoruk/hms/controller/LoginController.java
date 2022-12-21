package net.erenalyoruk.hms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import net.erenalyoruk.hms.App;
import net.erenalyoruk.hms.Main;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.provider.SceneType;
import net.erenalyoruk.hms.provider.SessionProvider;
import net.erenalyoruk.hms.service.AccountService;

public class LoginController {

    @FXML private TextField emailField;

    @FXML private PasswordField passwordField;

    @FXML private Text loginText;

    @FXML
    private void initialize() {
        loginText.setVisible(false);
    }

    @FXML
    private void login() {
        if (emailField.getText().isBlank() || passwordField.getText().isBlank()) {
            return;
        }

        AccountService accountService = Main.getAccountService();
        Account account =
                accountService.loginAccount(emailField.getText(), passwordField.getText());
        if (account != null) {
            SessionProvider.setAccount(account);
            App.getSceneProvider().setScene(SceneType.PROFILE);
        }

        loginText.setVisible(true);
    }
}
