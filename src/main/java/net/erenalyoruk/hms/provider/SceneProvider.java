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

    private final Map<SceneType, Scene> scenes;

    public SceneProvider(Stage root) {
        this.root = root;
        scenes = new HashMap<>();
    }

    public void setScene(SceneType type) {
        Scene scene =
                scenes.computeIfAbsent(
                        type,
                        t -> {
                            FXMLLoader loader =
                                    new FXMLLoader(Main.class.getResource("fxml/" + t.getUrl()));
                            try {
                                return new Scene(loader.load());
                            } catch (IOException exception) {
                                Logger.getAnonymousLogger()
                                        .log(
                                                Level.SEVERE,
                                                "Error while loading " + t.getUrl() + ".");
                                throw new RuntimeException(exception);
                            }
                        });

        root.setScene(scene);
        root.show();
    }

    public void resetScenes() {
        scenes.clear();
    }
}
