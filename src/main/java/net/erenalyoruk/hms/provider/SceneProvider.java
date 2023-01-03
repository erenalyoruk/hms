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

/** Provides scene to application. The list of scenes fetched from SceneType enum class. */
public class SceneProvider {

    private final Stage root;

    private final Map<SceneType, Scene> scenes;

    /**
     * Create provider to root stage.
     *
     * @param root Root stage of application.
     */
    public SceneProvider(Stage root) {
        this.root = root;
        scenes = new HashMap<>();
    }

    /**
     * If no scene created previously of type, then, creates scene of type. Otherwise, it returns
     * the scene from cache.
     *
     * @param type Scene type of scene. These statically defined in the enumeration.
     */
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

    /** Clears cached scenes. */
    public void resetScenes() {
        scenes.clear();
    }
}
