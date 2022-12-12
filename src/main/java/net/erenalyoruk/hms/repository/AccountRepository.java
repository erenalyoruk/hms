package net.erenalyoruk.hms.repository;

import net.erenalyoruk.hms.model.Account;

public interface AccountRepository {

    Account findById(int id);

    Account findByEmail(String email);

    Account findBySecurityNumber(String securityNumber);

    void insertOne(Account account);
}
