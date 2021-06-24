/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.defender.App;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
    private JFXButton btnMenos;
    @FXML
    private AnchorPane Target;
    @FXML
    private JFXButton btnMaas;
    @FXML
    private ImageView imvBallesta;
    double initMx, initMy,initX, initY;
    
    
    private double progresso;
    private double valor;
 
    private Juego game;
    private GameDto gameDto;
    @FXML
    private ImageView imvFire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        game = new Juego();
        gameDto = new GameDto();
        
        (new Thread(){
          public void run(){
          com.sun.javafx.application.PlatformImpl.startup(()->{});
      
          Media media = new Media(App.class.getResource("resources/InGameMusic.mp3").toString());
          MediaPlayer player = new MediaPlayer(media);
          player.setAutoPlay(true);
          }
        }).start();
        
        game = new Juego(lblPuntaje, lblNivel);
        AnchorPane pane = new AnchorPane();
        pane.setPadding(new Insets(0, 0, 0, 0));
        pane.getChildren().add(game);    
        
        root.setCenter(pane);   
    } 

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnPausa(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("MenuGame", this.getStage(), Boolean.TRUE);
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

    @FXML
    private void onDragDetectedHechizoFuego(MouseEvent event) {
        
        Image hechizoImage = new Image("cr/ac/una/defender/resources/hechizoDefenderFuego.jpg");
	ImageView hechizo = new ImageView(hechizoImage);
        /*
        Dragboard db = hechizo.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(hechizo.getImage());
        event.consume();*/
    }

    @FXML
    private void onDragDetectedHechizoHielo(MouseEvent event) {
    }

    @FXML
    private void onDragOverTarget(DragEvent event) {
        if(event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void onDragDroppedTarget(DragEvent event) {
        Image hechizoImage = new Image("hechizoDefenderFuego.jpg");
	ImageView hechizo = new ImageView(hechizoImage);
	hechizo.setTranslateX(event.getX() - 55);
	hechizo.setTranslateY(event.getY() - 25);
	Target.getChildren().add(hechizo);
	event.setDropCompleted(true);
	event.consume();
    }

    @FXML
    private void onMouseMovedBtnHechizoFuego(MouseEvent event) {
    
    }
}