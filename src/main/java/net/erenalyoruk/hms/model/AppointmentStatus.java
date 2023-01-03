package net.erenalyoruk.hms.model;

/** Status codes of appointments. */
public enum AppointmentStatus {
    /** Status of passed appointment. */
    MISSED,

    /** Status of appointment that is in the future. */
    WAITING,

    /** Status of fulfilled appoitnemnt. */
    COMPLETED
}
