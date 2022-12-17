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

    public boolean canLogin(String email, String unhashedPassword) {
        try {
            Account temp = repository.findOneByEmail(email);
            return temp.getPassword().equals(HashingUtil.sha256(unhashedPassword));
        } catch (NoResultException exception) {
            return false;
        }
    }
}
