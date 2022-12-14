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
    @Id
    private Long id;

    @MapsId
    @OneToOne(mappedBy = "patient")
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "patient")
    @Column(name = "appointments", nullable = false)
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "patient")
    @Column(name = "prescriptions", nullable = false)
    private List<Prescription> prescriptions = new ArrayList<>();
}
