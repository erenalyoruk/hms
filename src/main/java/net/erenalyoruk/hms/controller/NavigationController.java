package net.erenalyoruk.hms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.erenalyoruk.hms.App;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.provider.SceneType;
import net.erenalyoruk.hms.provider.SessionProvider;
import net.erenalyoruk.hms.provider.SessionType;

public class NavigationController {

    @FXML private Button switchButton;

    @FXML private Button adminButton;

    @FXML
    private void initialize() {
        Account account = SessionProvider.getAccount();
        SessionType sessionType = SessionProvider.getSessionType();

        if (!account.isAdmin()) {
            adminButton.setManaged(false);
        }

        if (!account.isDoctor()) {
            switchButton.setManaged(false);
        }

        if (sessionType == SessionType.DOCTOR) {
            switchButton.setText("Switch to Patient");
        } else if (sessionType == SessionType.PATIENT) {
            switchButton.setText("Switch to Doctor");
        }
    }

    @FXML
    private void appointments() {
        if (SessionProvider.getSessionType() == SessionType.DOCTOR) {
            App.getSceneProvider().setScene(SceneType.DOCTOR_APPOINTMENT_LIST);
            return;
        }

        App.getSceneProvider().setScene(SceneType.PATIENT_APPOINTMENT_LIST);
    }

    @FXML
    private void profile() {
        App.getSceneProvider().setScene(SceneType.PROFILE);
    }

    @FXML
    private void switchView() {
        if (SessionProvider.getSessionType() == SessionType.DOCTOR) {
            SessionProvider.setSessionType(SessionType.PATIENT);
        } else {
            SessionProvider.setSessionType(SessionType.DOCTOR);
        }

        App.getSceneProvider().resetScenes();
        App.getSceneProvider().setScene(SceneType.PROFILE);
    }

    @FXML
    private void admin() {}

    @FXML
    private void logout() {
        SessionProvider.setAccount(null);
        App.getSceneProvider().resetScenes();
        App.getSceneProvider().setScene(SceneType.AUTH);
    }
}
