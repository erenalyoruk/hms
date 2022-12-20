package net.erenalyoruk.hms.provider;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.erenalyoruk.hms.Main;

public class SceneProvider {

    private final Stage root;

    public SceneProvider(Stage root) {
        this.root = root;
    }

    public void setScene(SceneType type) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/" + type.getUrl()));
        Scene scene;

        try {
            scene = new Scene(loader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        root.setScene(scene);
        root.show();
    }
}
