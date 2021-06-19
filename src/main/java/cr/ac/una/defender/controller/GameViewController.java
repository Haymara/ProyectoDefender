/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.defender.controller.clases.Juego;
import cr.ac.una.defender.model.GameDto;
import cr.ac.una.defender.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Haymara
 */
public class GameViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnPausa;
    @FXML
    private Label lblPuntaje;
    @FXML
    private Label lblNivel;
    @FXML
    private ImageView imvProgreso;
    @FXML
    private ProgressBar pgbSaludCastillo;
    @FXML
    private ProgressBar pgbElixir;
    @FXML
    private JFXButton btnHechizoFuego;
    @FXML
    private JFXButton btnHechizoHielo;
    @FXML
    private BorderPane root;
    @FXML
    private Label lblPuntajex2;
    @FXML
    private Label lblNivelx2;
    @FXML
    private JFXButton btnMaas;
    @FXML
    private JFXButton btnMenos;
    @FXML
    private ImageView imvBallesta;
    double initMx, initMy,initX, initY;
    
    
    private double progresso;
    private double valor;
 
     private Juego tablero;
     private GameDto gameDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblPuntaje.setLabelFor(tablero);
       // nuevaPartida();
        tablero = new Juego();
        
        /*(new Thread(){
          public void run(){
          com.sun.javafx.application.PlatformImpl.startup(()->{});
      
          Media media = new Media(App.class.getResource("resources/InGameMusic.mp3").toString());
          MediaPlayer player = new MediaPlayer(media);
          player.setAutoPlay(true);
          }
        }).start();*/
        
        tablero = new Juego(lblPuntaje, lblNivel);
        AnchorPane pane = new AnchorPane();
        pane.setPadding(new Insets(0, 0, 0, 0));
        pane.getChildren().add(tablero);    
        
        root.setCenter(pane);
        
    } 
    
    public void bindPartida(Boolean nuevo){
        lblPuntaje.textProperty().bindBidirectional(gameDto.puntaje);
        
    }
    
    public void unbindPartida(){
        lblPuntaje.textProperty().unbindBidirectional(gameDto.puntaje);
    }
    
    private void nuevaPartida(){
  //  unbindPartida();
    gameDto = new GameDto();
    //bindPartida(true);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnPausa(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("MenuGame", this.getStage(), Boolean.TRUE);
    }

    @FXML
    private void onActionHechizoFuego(ActionEvent event) {
    }

    @FXML
    private void onActionHechizoHielo(ActionEvent event) {
    }

    @FXML
    private void onMouseClickedImvBallesta(MouseEvent event) {
    }

    @FXML
    private void onActionBtnMas(ActionEvent event) {
        if(valor<1){
        valor = ++progresso/100;
        pgbSaludCastillo.setProgress(valor);
    } System.out.println(valor);
    }

    @FXML
    private void onActionBtnMenos(ActionEvent event) {
        if(valor>(-0.2)){
        valor = --progresso/100;
        pgbSaludCastillo.setProgress(valor);
    }
          System.out.println(valor);
    }
}