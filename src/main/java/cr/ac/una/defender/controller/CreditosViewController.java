package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.defender.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Haymara
 */
public class CreditosViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnSalir;

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
        FlowController.getInstance().goViewInWindow("LoginView");
    }

    @Override
    public void initialize() {
    }
    
}
