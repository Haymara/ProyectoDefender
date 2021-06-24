/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.defender.model.GameDto;
import cr.ac.una.defender.util.FlowController;
import cr.ac.una.defender.util.Mensaje;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author duwan
 */
public class MejorasViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnTorre;
    @FXML
    private JFXButton btnCastillo;
    @FXML
    private JFXButton btnElixir;
    @FXML
    private JFXButton btnTorreAd;
    @FXML
    private JFXButton btnTorreRango;
    @FXML
    private JFXButton btnHechizoFuego;
    @FXML
    private JFXButton btnHizoHielo;
    @FXML
    private Label lblPuntuje;
    @FXML
    private Label lblNivel;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private Label lblMejora;
    @FXML
    private Label lblMejoraInfo;
    @FXML
    private Label lblActual;
    @FXML
    private Label lblNext;
    @FXML
    private Label lblValorMejora;
    @FXML
    private JFXButton btnUpgrade;
    
    private int saludCastilloNivel, elixirNivel, ballestaRangoNivel, ballestaAdNivel, hechizoHieloNivel, hechizoFuegoNivel, valorMejora;

    private GameDto gameDto;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void OnMouseClikedBtnTorre(MouseEvent event) {
        efectoBotones();
        lblMejora.setText("Defender");
        lblMejoraInfo.setText("Shi");
        lblActual.setText("Actual:");
        lblNext.setText("Next: ");      
    }

    @FXML
    private void OnMouseClikedCastillo(MouseEvent event) {
        efectoBotones();
        lblMejora.setText("Castillo");
        lblMejoraInfo.setText("Incrementa puntos de vida");
        lblActual.setText("Actual:");
        lblNext.setText("Next: ");
        
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            if(saludCastilloNivel < 10 && gameDto.getPuntaje() > valorMejora){
                saludCastilloNivel++; 
            } else{
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validacion de mejora", getStage(), "El valor de la mejora es mayor que el puntaje actual o el nivel maximo de la mejora alcanzado.");
            }
        }
    }

    @FXML
    private void OnMouseClickedBtnElixir(MouseEvent event) {
        efectoBotones();
        lblMejora.setText("Torre Magica");
        lblMejoraInfo.setText("Incrementa el elixir");
        lblActual.setText("Actual:");
        lblNext.setText("Next: ");
        
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            if(elixirNivel < 10 && gameDto.getElixir() > valorMejora){
                elixirNivel++; 
            } else{
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validacion de mejora", getStage(), "El valor de la mejora es mayor que el puntaje actual o el nivel maximo de la mejora alcanzado.");
            }
        }
        
    }

    @FXML
    private void OnMouseClikedBtnTorreAd(MouseEvent event) {
        efectoBotones();
        lblMejora.setText("TorreAd");
        lblMejoraInfo.setText("Incrementa los pichazos de la torre");
        lblActual.setText("Actual:");
        lblNext.setText("Next: ");
        
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            if(ballestaAdNivel < 10 && gameDto.getBallestaAd() > valorMejora){
                ballestaAdNivel++; 
            } else{
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validacion de mejora", getStage(), "El valor de la mejora es mayor que el puntaje actual o el nivel maximo de la mejora alcanzado.");
            }
        }
    }

    @FXML
    private void OnMousedClickedBtnTorreRango(MouseEvent event) {
        efectoBotones();
        lblMejora.setText("Torre Rango");
        lblMejoraInfo.setText("Incrementa la distancia del vergazo");
        lblActual.setText("Actual:");
        lblNext.setText("Next: ");
        
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            if(ballestaRangoNivel < 10 && gameDto.getBallestaCd() > valorMejora){
                ballestaRangoNivel++; 
            } else{
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validacion de mejora", getStage(), "El valor de la mejora es mayor que el puntaje actual o el nivel maximo de la mejora alcanzado.");
            }
        }
        
    }

    @FXML
    private void OnMousedClickedBtnHechizoFuego(MouseEvent event) {
        efectoBotones();
        lblMejora.setText("Fire Ball");
        lblMejoraInfo.setText("Hechizo Fuego. Explosion de fuego");
        lblActual.setText("Incrementa el daño");
        lblNext.setVisible(false);
        
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            if(hechizoFuegoNivel < 10 && gameDto.getBallestaCd() > valorMejora){
                hechizoFuegoNivel++; 
            } else{
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validacion de mejora", getStage(), "El valor de la mejora es mayor que el puntaje actual o el nivel maximo de la mejora alcanzado.");
            }
        }
        
    }

    @FXML
    private void OnMousedClickedHechizoHielo(MouseEvent event) {
        efectoBotones();
        lblMejora.setText("Glacial Spike");
        lblMejoraInfo.setText("Hechizo Hielo. Congela monstruos");
        lblActual.setText("Incrementa el daño");
        lblNext.setVisible(false);
        
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            if(hechizoHieloNivel < 10 && gameDto.getBallestaCd() > valorMejora){
                hechizoHieloNivel++; 
            } else{
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validacion de mejora", getStage(), "El valor de la mejora es mayor que el puntaje actual o el nivel maximo de la mejora alcanzado.");
            }
        }
        
    }

    @FXML
    private void OnActionBtnContinuar(ActionEvent event) {
        efectoBotones();
        FlowController.getInstance().goViewInWindow("GameView");
    }
    
    @FXML
    private void onActionBtnTorreAd(ActionEvent event) {
    }

    @FXML
    private void onActionTorreRango(ActionEvent event) {
    }

    @FXML
    private void onActionBtnUpgrade(ActionEvent event) {
    }
    
    @Override
    public void initialize() {
    }
}
