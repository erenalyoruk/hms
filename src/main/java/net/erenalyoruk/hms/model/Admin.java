package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
public class Admin {
    @Id private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;
}
