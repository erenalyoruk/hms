package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

    @Id
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany
    @JoinColumn(name = "clinic_id")
    private Set<Clinic> clinics = new HashSet<>();

    public Doctor(Account account, Set<Clinic> clinics) {
        this.account = account;
        this.clinics = clinics;
    }
}
