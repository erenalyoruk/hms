package net.erenalyoruk.hms.provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.erenalyoruk.hms.Main;

public class SceneProvider {

    private final Stage root;

    private final Map<SceneType, Scene> scenes = new HashMap<>();

    public SceneProvider(Stage root) {
        this.root = root;
        for (SceneType type : SceneType.values()) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/" + type.getUrl()));
            try {
                scenes.put(type, new Scene(loader.load()));
            } catch (IOException exception) {
                Logger.getAnonymousLogger().log(Level.SEVERE, type.getUrl() + " not found!");
            }
        }
    }

    public Scene getScene(SceneType type) {
        return scenes.get(type);
    }

    public void setScene(SceneType type) {
        Scene scene = getScene(type);
        if (scene == null) {
            return;
        }

        root.setScene(scene);
        root.show();
    }
}
