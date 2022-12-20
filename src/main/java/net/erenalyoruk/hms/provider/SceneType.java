package net.erenalyoruk.hms.provider;

public enum SceneType {
    LOGIN("login.fxml");

    private final String url;

    SceneType(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
