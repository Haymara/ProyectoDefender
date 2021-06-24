package cr.ac.una.defender;

import cr.ac.una.defender.util.FlowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
      FlowController.getInstance().InitializeFlow(stage, null);
      stage.setTitle("DEFENDER");
      FlowController.getInstance().goViewInWindow("loginView");//para mostrar por medio de un flowcontroller una vista en una ventana nueva
    }

    public static void main(String[] args) {
        launch();
    }

}