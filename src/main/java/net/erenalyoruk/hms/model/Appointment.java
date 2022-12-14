package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "account_id")
    private Account patient;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status")
    private AppointmentStatus status = AppointmentStatus.WAITING;

    @Column(name = "appointment_timestamp")
    private Timestamp timestamp;

    public Appointment(Account patient, Doctor doctor, Timestamp timestamp) {
        this.patient = patient;
        this.doctor = doctor;
        this.timestamp = timestamp;
    }
}
