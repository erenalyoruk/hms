package net.erenalyoruk.hms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "security_no", unique = true)
    private String securityNumber;

    public Account(String email, String securityNumber, String password) {
        this.email = email;
        this.securityNumber = securityNumber;
        this.password = password;
    }
}
