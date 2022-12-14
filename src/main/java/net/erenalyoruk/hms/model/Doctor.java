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
    @Id
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany
    private List<Clinic> clinics = new ArrayList<>();

    public Doctor(Account account, List<Clinic> clinics) {
        this.account = account;
        this.clinics = clinics;
    }
}
