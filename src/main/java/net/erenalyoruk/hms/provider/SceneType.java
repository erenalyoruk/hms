package net.erenalyoruk.hms.provider;

public enum SceneType {
    AUTH("auth.fxml"),
    PROFILE("profile.fxml"),
    PATIENT_APPOINTMENT_LIST("patient/appointment_list.fxml"),
    DOCTOR_APPOINTMENT_LIST("doctor/appointment_list.fxml");

    private final String url;

    SceneType(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
