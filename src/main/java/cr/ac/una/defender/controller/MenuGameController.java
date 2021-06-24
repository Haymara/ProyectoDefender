package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.defender.model.GameDto;
import cr.ac.una.defender.service.PartidaService;
import cr.ac.una.defender.util.FlowController;
import cr.ac.una.defender.util.Mensaje;
import cr.ac.una.defender.util.Respuesta;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Haymara
 */
public class MenuGameController extends Controller implements Initializable {

    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXButton btnReiniciar;
    @FXML
    private JFXButton btnContinuar;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnGuardar;
    
    private GameDto gameDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        efectoBotones();
        getStage().close();
        FlowController.getInstance().goViewInWindow("MenuPartidaView");
    }

    @FXML
    private void onActionBtnReiniciar(ActionEvent event) {
        efectoBotones();
    }
    
    @FXML
    private void onActionBtnContinuar(ActionEvent event) {
        efectoBotones();
        getStage().close();
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
                PartidaService service = new PartidaService();
                Respuesta respuesta = service.guardarGame(gameDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), respuesta.getMensaje());
                } else {
                    gameDto = (GameDto) respuesta.getResultado("Empleado");
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar empleado", getStage(), "Empleado actualizado correctamente.");
                }
            } catch (Exception ex) {
            Logger.getLogger(MenuGameController.class.getName()).log(Level.SEVERE, "Error guardando el empleado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), "Ocurrio un error guardando el empleado.");
        }
    }
    
    @Override
    public void initialize() {
    }
}
