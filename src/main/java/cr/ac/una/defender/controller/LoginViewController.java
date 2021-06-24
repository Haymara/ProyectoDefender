package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.defender.App;
import cr.ac.una.defender.util.AppContext;
import cr.ac.una.defender.util.FlowController;
import cr.ac.una.defender.util.Mensaje;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author Haymara
 */
public class LoginViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXButton btnIngresar;
    @FXML
    private JFXButton btnCreditos;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private Label labelNuevoUsu;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblLogin;
    @FXML
    private JFXButton btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        (new Thread(){
          public void run(){
          com.sun.javafx.application.PlatformImpl.startup(()->{});
      
          Media media = new Media(App.class.getResource("resources/MusicaFondoDefender.wav").toString());
          MediaPlayer player = new MediaPlayer(media);
          player.setAutoPlay(true);
          player.setCycleCount(MediaPlayer.INDEFINITE);
          }
        }).start();
    }    

    @FXML
    private void onActionBtnIngresar(ActionEvent event) {
         try{
            
            efectoBotones();
             
            if(txtUsuario.getText() == null || txtUsuario.getText().isEmpty()){
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validacion de usuario", getStage(), "Es necesario digitar un usuario para ingresar al sistema.");
          }else{
                AppContext.getInstance().set("Usuario", txtUsuario.getText());
               //FlowController.getInstance().goMain(); para ingresar a la ventana principal
                getStage().close();
               FlowController.getInstance().goMain();
               
            }
        }catch(Exception ex){
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error ingresando", ex);
        }
    }


    @FXML
    private void onActionBtnCreditos(ActionEvent event) {
        efectoBotones();
        getStage().close();
        FlowController.getInstance().goViewInWindow("CreditosView");
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        efectoBotones();
        getStage().close();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {
        efectoBotones();
        txtUsuario.clear();
    }

    @FXML
    private void onActionLabelNuevoUsu(MouseEvent event) {
        efectoBotones();
        FlowController.getInstance().goViewInWindowModal("Registro", this.getStage(), Boolean.TRUE);
    }   
}
