package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private Long id;

    @OneToOne(mappedBy = "prescription")
    @JoinColumn(name = "appointment_id", unique = true, nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "account_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "account_id", nullable = false)
    private Doctor doctor;

    @Column(name = "info", length = 65535)
    private String details;
}
