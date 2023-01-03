package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "doctor")
    @Column(name = "appointments")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "doctor")
    @Column(name = "prescriptions")
    private List<Prescription> prescriptions = new ArrayList<>();

    /**
     * Add appointment to doctor.
     *
     * @param appointment Appointment
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setDoctor(this);
    }

    /**
     * Remove appointment from doctor.
     *
     * @param appointment Appointment
     */
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setDoctor(null);
    }

    /**
     * Add prescription that written by doctor.
     *
     * @param prescription Prescription
     */
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
        prescription.setDoctor(this);
    }

    /**
     * Remove prescription that written by doctor.
     *
     * @param prescription Prescription
     */
    public void removePrescription(Prescription prescription) {
        prescriptions.remove(prescription);
        prescription.setDoctor(null);
    }
}
