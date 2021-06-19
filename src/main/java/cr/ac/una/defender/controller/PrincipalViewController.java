/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.defender.App;
import cr.ac.una.defender.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static sun.misc.PostVMInitHook.run;
/**
 * FXML Controller class
 *
 * @author duwan
 */
public class PrincipalViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnInicio;
    @FXML
    private JFXButton btnPuntaje;
    @FXML
    private JFXButton btnBallesta;
    @FXML
    private JFXButton btnHelp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
    }


    @FXML
    private void onActionBtnInicio(ActionEvent event) {
        efectoBotones();
        /*Media media = new Media(App.class.getResource("resources/InGameMusic.wav").toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setAutoPlay(true);*/
        FlowController.getInstance().goViewInWindow("GameView");
    }

    @FXML
    private void onActionBtnPuntaje(ActionEvent event) {
        efectoBotones();
    }

    @FXML
    private void onActionBtnBallesta(ActionEvent event) {
        efectoBotones();
    }

    @FXML
    private void onActionBtnHelp(ActionEvent event) {
        efectoBotones();
        FlowController.getInstance().goViewInWindow("HelpView");
    }
    
}
