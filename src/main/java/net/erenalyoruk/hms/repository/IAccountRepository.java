package net.erenalyoruk.hms.repository;

import java.util.List;
import net.erenalyoruk.hms.model.Account;
import net.erenalyoruk.hms.model.Gender;

public interface IAccountRepository {
    /**
     * Finds all accounts in database.
     *
     * @return List of accounts.
     */
    List<Account> findAll();

    /**
     * Finds unique account by specified unique id.
     *
     * @param id Unique id of account.
     * @return Account.
     */
    Account findOneById(int id);

    /**
     * Finds unique account by specified email.
     *
     * @param email Email of account.
     * @return Account.
     */
    Account findOneByEmail(String email);

    /**
     * Finds account by specified security number.
     *
     * @param securityNumber Security number of account.
     * @return Account.
     */
    Account findOneBySecurityNumber(String securityNumber);

    /**
     * Finds accounts by first name.
     *
     * @param firstName First name of accounts.
     * @return List of accounts.
     */
    List<Account> findManyByFirstName(String firstName);

    /**
     * Finds accounts by last name.
     *
     * @param lastName Last name of accounts.
     * @return List of accounts.
     */
    List<Account> findManyByLastName(String lastName);

    /**
     * Finds accounts by age.
     *
     * @param age Specified age to be searched through database.
     * @return List of accounts.
     */
    List<Account> findManyByAge(int age);

    /**
     * Finds accounts by gender.
     *
     * @param gender Specified gender to be searched through database.
     * @return List of accounts.
     */
    List<Account> findManyByGender(Gender gender);

    /**
     * Add account to the database. Email and security number must be unique in database.
     *
     * @param account Account to be inserted.
     */
    void insertOne(Account account);
}
