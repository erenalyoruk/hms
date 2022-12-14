package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "patient")
    @CollectionTable(name = "appointments")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "patient")
    @CollectionTable(name = "prescriptions")
    private List<Prescription> prescriptions = new ArrayList<>();

    /**
     * Add appointment to patient.
     *
     * @param appointment Appointment
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPatient(this);
    }

    /**
     * Remove appointment from patient.
     *
     * @param appointment Appointment
     */
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setPatient(null);
    }

    /**
     * Add prescription that written to patient.
     *
     * @param prescription Prescription
     */
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
        prescription.setPatient(this);
    }

    /**
     * Remove prescription that written to patient.
     *
     * @param prescription Prescription
     */
    public void removePrescription(Prescription prescription) {
        prescriptions.remove(prescription);
        prescription.setPatient(null);
    }
}
