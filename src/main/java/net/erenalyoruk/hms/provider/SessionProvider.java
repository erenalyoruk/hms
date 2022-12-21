package net.erenalyoruk.hms.provider;

import net.erenalyoruk.hms.model.Account;

public class SessionProvider {

    private static Account account;

    private static SessionType sessionType;

    public SessionProvider() {}

    public static void setAccount(Account account) {
        SessionProvider.account = account;
        SessionProvider.sessionType = SessionType.PATIENT;
    }

    public static void setSessionType(SessionType sessionType) {
        if (account == null) {
            return;
        }

        if (sessionType.equals(SessionType.DOCTOR) && account.isDoctor()) {
            SessionProvider.sessionType = SessionType.DOCTOR;
        } else if (sessionType.equals(SessionType.PATIENT)) {
            SessionProvider.sessionType = SessionType.PATIENT;
        }
    }

    public static Account getAccount() {
        return account;
    }

    public static SessionType getSessionType() {
        return sessionType;
    }
}
