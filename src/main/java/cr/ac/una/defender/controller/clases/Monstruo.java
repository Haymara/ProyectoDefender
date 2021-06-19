/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller.clases;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Haymara
 */
public class Monstruo extends ImageView {
    
    private int numero;
    private String opcion;
    private float posx;
    private float posy;
    private int tamx;
    private int tamy;
    private int reverso=1;
    public String URL=null;
    public static final String imagenBallesta= "cr/ac/una/defender/resources/BallestaAzul.png";
    public static final String BallestaUno= "cr/ac/una/defender/resources/BallestaAzul.png";
  
    public static final String[] Opcion={"BallestaRoja"};
    public static final String[] Opncion1={"BallestaAzul"};
    
    public Monstruo(int numero, String opcion, int posx, int posy) {
        this.numero = numero;
        this.opcion = opcion;
    }

    public Monstruo(int numero, String opcion, float posx, float posy) {
        this.numero = numero;
        this.opcion = opcion;
        this.posx = posx;
        this.posy = posy;
        
    }

    public Monstruo(int numero, String opcion, float posx, float posy, int tamx, int tamy) {
        this.numero = numero;
        this.opcion = opcion;
        this.posx = posx;
        this.posy = posy;
        this.tamx = tamx;
        this.tamy = tamy;          
    }

    Monstruo() {
    }

    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public float getPosx() {
        return posx;
    }

    public void setPosx(float posx) {
        this.posx = posx;
    }

    public float getPosy() {
        return posy;
    }

    public void setPosy(float posy) {
        this.posy = posy;
    }

    public int getTamx() {
        return tamx;
    }

    public void setTamx(int tamx) {
        this.tamx = tamx;
    }

    public int getTamy() {
        return tamy;
    }

    public void setTamy(int tamy) {
        this.tamy = tamy;
    }

    public String getImagenBallesta() {
        return imagenBallesta;
    }

    public String getBallestaUno() {
        return BallestaUno;
    }
    
    @Override
    public String toString() {
        return "Card{" + "numero=" + numero + ", palo=" + opcion + '}';
    } 
}
