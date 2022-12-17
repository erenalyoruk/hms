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
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "account_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "account_id")
    private Doctor doctor;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppointmentStatus status = AppointmentStatus.WAITING;

    public void createPrescription(String details) {
        Prescription prescription = new Prescription();
        prescription.setAppointment(this);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setDetails(details);
        setPrescription(prescription);
    }

    public void removePrescription() {
        prescription.getPatient().removePrescription(prescription);
        prescription.getDoctor().removePrescription(prescription);
        prescription.setAppointment(null);
        setPrescription(null);
    }
}
