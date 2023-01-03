package net.erenalyoruk.hms.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;
import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.repository.AccountRepository;
import net.erenalyoruk.hms.util.HashingUtil;

/** Layer between repository and application. */
public class AccountService {
    private final AccountRepository repository;

    /**
     * Create service for repository.
     *
     * @param repository AccountRepository
     */
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    /**
     * Find all accounts that is doctor.
     *
     * @return List of doctor accounts.
     */
    public List<Account> findAllDoctors() {
        return repository.findDoctors();
    }

    /**
     * Create account and save it.
     *
     * @param account Account that will be created.
     * @return Status of success.
     */
    public boolean createAccount(Account account) {
        try {
            repository.save(account);
            return true;
        } catch (EntityExistsException exception) {
            return false;
        }
    }

    /**
     * Find account by email.
     *
     * @param email Email of account
     * @return Status of operation.
     */
    public boolean isEmailUnique(String email) {
        try {
            Account account = repository.findOneByEmail(email);
            return false;
        } catch (NoResultException exception) {
            return true;
        }
    }

    /**
     * Find account by citizen number.
     *
     * @param citizenNumber Citizen number of account.
     * @return Status of success.
     */
    public boolean isCitizenNumberUnique(String citizenNumber) {
        try {
            Account account = repository.findOneByCitizenNumber(citizenNumber);
            return false;
        } catch (NoResultException exception) {
            return true;
        }
    }

    /**
     * Checks if there is an account with these information.
     *
     * @param email Email of account.
     * @param unhashedPassword Raw password.
     * @return Account if there is an account with these information. Otherwise, null.
     */
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
