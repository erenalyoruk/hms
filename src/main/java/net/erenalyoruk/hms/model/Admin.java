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
    @Id
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Admin(Account account) {
        this.account = account;
    }
}
