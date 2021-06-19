package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.defender.service.JugadorService;
import cr.ac.una.defender.util.Formato;
import cr.ac.una.defender.util.Mensaje;
import cr.ac.una.defender.util.Respuesta;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import cr.ac.una.defender.model.JugadorDto;

/**
 * FXML Controller class
 *
 * @author Haymara
 */
public class RegistroController extends Controller implements Initializable {

    /*@FXML
    private JFXTextField txtId;*/
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXButton btnNuevo;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXButton btnAvatar;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imvAvatar;
    
    private JugadorDto jugadorDto;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txtUsuario.setTextFormatter(Formato.getInstance().letrasFormat(15));
       jugadorDto = new JugadorDto();
       nuevoUsuario();
    }    

      
    private void bindUsuario(Boolean nuevo){
        txtUsuario.textProperty().bindBidirectional(jugadorDto.nombreUsuario);
    }
    
    private void unbindUsuario(){
        txtUsuario.textProperty().unbindBidirectional(jugadorDto.nombreUsuario);
    }
    
    private void nuevoUsuario(){
        unbindUsuario();
        jugadorDto = new JugadorDto();
        bindUsuario(true);
        txtUsuario.clear();
        txtUsuario.requestFocus();
    }
    
    private void cargarUsuario(Long id) {
        JugadorService service = new JugadorService();
        Respuesta respuesta = service.getUsuario(id);

        if (respuesta.getEstado()) {
            unbindUsuario();
            jugadorDto = (JugadorDto) respuesta.getResultado("Jugador");
            bindUsuario(false);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar usuario", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        efectoBotones();
   if(new Mensaje().showConfirmation("Limpiar Registro", getStage(),"¿Estás seguro que desea limpiar el registro?" )){
    
        Image image = new Image("\\cr\\ac\\una\\defender\\resources\\UserIcon.png");
        imvAvatar.setImage(image);
        }
        txtUsuario.clear();
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        efectoBotones();
          getStage().close();
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        efectoBotones();
        try{
            JugadorService service = new JugadorService();
            Respuesta respuesta = service.guardarJugador(jugadorDto);
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), respuesta.getMensaje());
            } else {
            unbindUsuario();
            jugadorDto = (JugadorDto) respuesta.getResultado("Jugador");
            bindUsuario(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar usuario", getStage(), "Usuario actualizado correctamente.");
            }
        }
            catch (Exception ex) {
            Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar usuario", getStage(), "Ocurrio un error guardando el usuario.");
        }
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAvatar(ActionEvent event) {
        efectoBotones();
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Explorador de archivos");
       // Stage stage = (Stage)root.getScene().getWindow();
        //filechooser.showOpenDialog(stage);
  

       Window stage1 = null;

        // Obtiene la imagen seleccionada  
       File imgFile = filechooser.showOpenDialog(stage1);

        // Muestra la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            imvAvatar.setImage(image);
        }
    }

    
}
