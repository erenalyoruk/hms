package net.erenalyoruk.hms;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import net.erenalyoruk.hms.provider.SceneProvider;
import net.erenalyoruk.hms.provider.SceneType;

public class App extends Application {

    private static SceneProvider sceneProvider;

    public static void start(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        sceneProvider = new SceneProvider(stage);
        sceneProvider.setScene(SceneType.AUTH);
    }

    public static SceneProvider getSceneProvider() {
        return sceneProvider;
    }
}
