package net.erenalyoruk.hms.provider;

public enum SceneType {
    LOGIN("login.fxml"),
    REGISTER("register.fxml"),
    AUTH("auth.fxml"),
    DUMMY("dummy.fxml");

    private final String url;

    SceneType(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
