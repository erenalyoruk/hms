package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import net.erenalyoruk.hms.util.HashingUtil;

/** Base class of user presentation. */
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

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "citizenNumber", nullable = false)
    private String citizenNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private Timestamp dateOfBirth;

    /** Grants patient permissions to account by default in constructor. */
    public Account() {
        Patient patient = new Patient();
        patient.setAccount(this);
        setPatient(patient);
    }

    /**
     * Sets password of account to raw string. Internally it hashes this raw string to SHA-256
     * format.
     *
     * @param password Raw password
     */
    public void setPassword(String password) {
        this.password = HashingUtil.sha256(password);
    }

    /** Grants doctor permissions to account. */
    public void makeDoctor() {
        Doctor doctor = new Doctor();
        setDoctor(doctor);
        doctor.setAccount(this);
    }

    /** Grants admin permissions to account. */
    public void makeAdmin() {
        Admin admin = new Admin();
        setAdmin(admin);
        admin.setAccount(this);
    }

    /** Checks if account has admin permissions. */
    public boolean isAdmin() {
        return admin != null;
    }

    /** Checks if account has doctor permissions. */
    public boolean isDoctor() {
        return doctor != null;
    }
}
