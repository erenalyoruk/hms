package net.erenalyoruk.hms.controller.doctor;

import java.time.LocalDateTime;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import net.erenalyoruk.hms.Main;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.AppointmentStatus;
import net.erenalyoruk.hms.provider.SessionProvider;

public class AppointmentListController {

    @FXML private TableView<Appointment> appointmentView;

    @FXML private TableColumn<Appointment, String> prescriptionColumn;

    @FXML private TableColumn<Appointment, String> dateColumn;

    @FXML private TableColumn<Appointment, String> patientColumn;

    @FXML private TableColumn<Appointment, AppointmentStatus> statusColumn;

    @FXML
    private void initialize() {
        dateColumn.setCellValueFactory(
                av -> {
                    LocalDateTime t = av.getValue().getTimestamp().toLocalDateTime();
                    String dateStr =
                            t.getDayOfMonth() + "." + (t.getMonthValue() - 1) + "." + t.getYear();
                    return new ReadOnlyObjectWrapper<>(dateStr);
                });
        patientColumn.setCellValueFactory(
                av -> {
                    Account account = av.getValue().getPatient().getAccount();
                    String name = account.getFirstName() + " " + account.getLastName();
                    return new ReadOnlyObjectWrapper<>(name);
                });
        prescriptionColumn.setCellValueFactory(
                av -> {
                    if (av.getValue().getPrescription() == null) {
                        return new ReadOnlyObjectWrapper<>("");
                    }

                    return new ReadOnlyObjectWrapper<>(
                            av.getValue().getPrescription().getDetails());
                });
        prescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prescriptionColumn.setOnEditCommit(
                event -> {
                    Appointment appointment =
                            event.getTableView().getItems().get(event.getTablePosition().getRow());
                    Main.getPrescriptionService()
                            .createPrescription(appointment, event.getNewValue());
                });

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(
                ComboBoxTableCell.forTableColumn(
                        FXCollections.observableArrayList(AppointmentStatus.values())));
        statusColumn.setOnEditCommit(
                event -> {
                    Appointment appointment =
                            event.getTableView().getItems().get(event.getTablePosition().getRow());
                    Main.getAppointmentService().setStatus(appointment, event.getNewValue());
                });

        Account account = SessionProvider.getAccount();
        List<Appointment> appointments = account.getDoctor().getAppointments();

        appointmentView.getItems().setAll(appointments);
    }
}
