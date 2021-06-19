/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void OnMouseClikedBtnTorre(MouseEvent event) {
        efectoBotones();
    }

    @FXML
    private void OnMouseClikedCastillo(MouseEvent event) {
        efectoBotones();
    }

    @FXML
    private void OnMouseClickedBtnElixir(MouseEvent event) {
        efectoBotones();
    }

    @FXML
    private void OnMouseClikedBtnTorreAd(MouseEvent event) {
        efectoBotones();
    }

    @FXML
    private void OnMousedClickedBtnTorreRango(MouseEvent event) {
        efectoBotones();
    }

    @FXML
    private void OnMousedClickedBtnHechizoFuego(MouseEvent event) {
        efectoBotones();
    }

    @FXML
    private void OnMousedClickedHechizoHielo(MouseEvent event) {
        efectoBotones();
    }

    @FXML
    private void OnActionBtnContinuar(ActionEvent event) {
        efectoBotones();
    }

    @Override
    public void initialize() {
    }
    
}
