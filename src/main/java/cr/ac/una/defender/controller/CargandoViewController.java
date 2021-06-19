/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller;

import com.jfoenix.controls.JFXProgressBar;
import cr.ac.una.defender.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;

/**
 * FXML Controller class
 *
 * @author duwan
 */
public class CargandoViewController extends Controller implements Initializable {

    @FXML
    private ProgressIndicator pgbCargando;
    
    double Contador=0.1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
         Thread t1 = new Thread(() -> { //definicion del thread
            synchronized(this){  //sincronizacion entre los hilos (t1 y t2)
                while(Contador < 1){
                    Platform.runLater(new Runnable() { 
                           @Override 
                           public void run() {
                                   pgbCargando.setProgress(Contador); 
                            }    
                    });
                     try {
                        Thread.sleep(100);
                        Contador+=0.01;
                    } catch (InterruptedException e) {}

                }
                notify();  //envia la notificacion de finalizacion 
            }
        });
        t1.start();
        
        Thread t2 = new Thread(() -> {
            synchronized(t1){
                try{
                    t1.wait(); //espera la notificacion 
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                 @Override public void run() {
                    getStage().close();
                    FlowController.getInstance().goViewInWindow("LoginView");
                    
                 }
                });
            } 
        });
        t2.start();
    }
}
