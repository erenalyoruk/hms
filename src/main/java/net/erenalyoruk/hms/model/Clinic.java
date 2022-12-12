package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clinics")
@Getter
@Setter
@NoArgsConstructor
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_id")
    private int id;

    @Column(name = "clinic_name")
    private String name;

    @ManyToMany(mappedBy = "clinics")
    private Set<Doctor> doctors = new HashSet<>();

    public Clinic(String name) {
        this.name = name;
    }
}
