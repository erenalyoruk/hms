package net.erenalyoruk.hms.controller.patient;

import java.sql.Timestamp;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.erenalyoruk.hms.Main;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Appointment;
import net.erenalyoruk.hms.model.Doctor;
import net.erenalyoruk.hms.model.Patient;
import net.erenalyoruk.hms.provider.SessionProvider;
import net.erenalyoruk.hms.util.DoctorStringConverter;

public class AppointmentListController {

    @FXML private DatePicker datePicker;

    @FXML private ChoiceBox<Doctor> doctorChoice;

    @FXML private TableView<Appointment> appointmentView;

    @FXML private TableColumn<Appointment, String> prescriptionColumn;

    @FXML private TableColumn<Appointment, String> dateColumn;

    @FXML private TableColumn<Appointment, String> doctorColumn;

    @FXML private TableColumn<Appointment, String> statusColumn;

    @FXML
    private void initialize() {
        dateColumn.setCellValueFactory(
                av -> {
                    LocalDateTime t = av.getValue().getTimestamp().toLocalDateTime();
                    String dateStr =
                            t.getDayOfMonth() + "." + (t.getMonthValue() - 1) + "." + t.getYear();
                    return new ReadOnlyObjectWrapper<String>(dateStr);
                });
        doctorColumn.setCellValueFactory(
                av -> {
                    Account account = av.getValue().getDoctor().getAccount();
                    String name = account.getFirstName() + " " + account.getLastName();
                    return new ReadOnlyObjectWrapper<String>(name);
                });
        prescriptionColumn.setCellValueFactory(
                av -> {
                    if (av.getValue().getPrescription() == null) {
                        return new ReadOnlyObjectWrapper<String>("");
                    }

                    return new ReadOnlyObjectWrapper<String>(
                            av.getValue().getPrescription().getDetails());
                });
        statusColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("status"));

        Account account = SessionProvider.getAccount();
        List<Appointment> appointments = account.getPatient().getAppointments();

        appointmentView.getItems().setAll(appointments);

        List<Account> accounts = Main.getAccountService().findAllDoctors();
        accounts.remove(account);

        List<Doctor> doctorList = accounts.stream().map(Account::getDoctor).toList();
        ObservableList<Doctor> doctors = FXCollections.observableArrayList(doctorList);
        doctorChoice.setConverter(new DoctorStringConverter());
        doctorChoice.setItems(doctors);
    }

    @FXML
    private void schedule() {
        if (datePicker.getValue().isBefore(ChronoLocalDate.from(ZonedDateTime.now()))) {
            return;
        }

        if (doctorChoice.getValue() == null) {
            return;
        }

        Patient patient = SessionProvider.getAccount().getPatient();
        Doctor doctor = doctorChoice.getValue();
        Timestamp timestamp;

        try {
            long date =
                    LocalDate.of(
                                    datePicker.getValue().getYear(),
                                    Month.values()[datePicker.getValue().getMonthValue() - 1],
                                    datePicker.getValue().getDayOfMonth())
                            .atStartOfDay(ZoneOffset.UTC)
                            .toInstant()
                            .toEpochMilli();
            timestamp = new Timestamp(date);
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }

        Appointment a = Main.getAppointmentService().createAppointment(patient, doctor, timestamp);
        appointmentView.getItems().add(a);
    }
}
