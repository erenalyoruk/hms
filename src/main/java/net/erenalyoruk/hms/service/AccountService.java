package net.erenalyoruk.hms.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.repository.AccountRepository;
import net.erenalyoruk.hms.util.HashingUtil;

public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean createAccount(Account account) {
        try {
            repository.save(account);
            return true;
        } catch (EntityExistsException exception) {
            return false;
        }
    }

    public boolean removeAccount(Account account) {
        try {
            repository.remove(account);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public boolean isEmailUnique(String email) {
        try {
            Account account = repository.findOneByEmail(email);
            return false;
        } catch (NoResultException exception) {
            return true;
        }
    }

    public boolean isCitizenNumberUnique(String citizenNumber) {
        try {
            Account account = repository.findOneByCitizenNumber(citizenNumber);
            return false;
        } catch (NoResultException exception) {
            return true;
        }
    }

    public Account loginAccount(String email, String unhashedPassword) {
        try {
            Account account;
            if (email.contains("@")) {
                account = repository.findOneByEmail(email);
            } else {
                account = repository.findOneByCitizenNumber(email);
            }

            if (account == null) {
                return null;
            }

            if (account.getPassword().equalsIgnoreCase(HashingUtil.sha256(unhashedPassword))) {
                return account;
            }
        } catch (NoResultException ignored) {
        }

        return null;
    }
}
