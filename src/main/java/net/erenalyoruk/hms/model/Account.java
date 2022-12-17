package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import net.erenalyoruk.hms.util.HashingUtil;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false, updatable = false)
    private Long id;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account", orphanRemoval = true)
    private Patient patient;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account", orphanRemoval = true)
    private Doctor doctor;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account", orphanRemoval = true)
    private Admin admin;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "citizenNumber", nullable = false)
    private String citizenNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private Timestamp dateOfBirth;

    public Account() {
        Patient patient = new Patient();
        patient.setAccount(this);
        setPatient(patient);
    }

    public void setPassword(String password) {
        this.password = HashingUtil.sha256(password);
    }

    public void makeDoctor() {
        Doctor doctor = new Doctor();
        setDoctor(doctor);
        doctor.setAccount(this);
    }

    public void makeAdmin() {
        Admin admin = new Admin();
        setAdmin(admin);
        admin.setAccount(this);
    }

    public boolean isAdmin() {
        return admin != null;
    }
}
