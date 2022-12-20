package net.erenalyoruk.hms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import net.erenalyoruk.hms.provider.SessionProvider;

public class DummyController {

    @FXML private TextArea dummyArea;

    @FXML
    private void initialize() {
        if (SessionProvider.getAccount() != null) {
            dummyArea.appendText("IM DUMMY! CHANGE ME PLS!");
            dummyArea.appendText(SessionProvider.getAccount().getCitizenNumber() + "\n");
            dummyArea.appendText(SessionProvider.getAccount().getEmail() + "\n");
            dummyArea.appendText(SessionProvider.getAccount().getFirstName() + "\n");
            dummyArea.appendText(SessionProvider.getAccount().getLastName() + "\n");
        }
    }
}
