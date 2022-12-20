package net.erenalyoruk.hms.provider;

import net.erenalyoruk.hms.model.Account;

public class SessionProvider {

    private static Account account;

    public SessionProvider() {}

    public static void setAccount(Account account) {
        SessionProvider.account = account;
    }

    public static Account getAccount() {
        return account;
    }
}
