package net.erenalyoruk.hms.provider;

import lombok.NoArgsConstructor;
import net.erenalyoruk.hms.model.Account;

/** Provider of session that used by application. */
@NoArgsConstructor
public class SessionProvider {
    // TODO: Singleton pattern

    private static Account account;

    private static SessionType sessionType;

    /**
     * Set account to current session context.
     *
     * @param account The account will be set to session context.
     */
    public static void setAccount(Account account) {
        SessionProvider.account = account;
        SessionProvider.sessionType = SessionType.PATIENT;
    }

    /**
     * Set session type of session context.
     *
     * @param sessionType Type of session.
     */
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

    /**
     * @return Account that assigned to session context.
     */
    public static Account getAccount() {
        return account;
    }

    /**
     * @return Session type that assigned to session context.
     */
    public static SessionType getSessionType() {
        return sessionType;
    }
}
