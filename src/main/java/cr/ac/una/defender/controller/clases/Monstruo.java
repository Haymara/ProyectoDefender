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
    private String dificultad;
    private float posx;
    private float posy;
    private int tamx;
    private int tamy;
    private int reverso=1;
    private Boolean oculta;
    public String URL=null;
    public static final String imagenMonstruo=  "cr/ac/una/defender/resources/game/m1.gif";
    public static final String imagenMonstruo1= "cr/ac/una/defender/resources/game/start.png";
    public static final String imagenMonstruo2= "cr/ac/una/defender/resources/game/m1.gif";
    public static final String imagenMonstruo3= "cr/ac/una/defender/resources/game/m1.gif";
    public static final String imagenMonstruo4=  "cr/ac/una/defender/resources/game/m1.gif";
    
    
    
    public static final int Limite_Monstruo_Dificultad = 13;
  
  
    public static final String[] DIFIC3={"picas","diamantes","trebol","corazones","picas","diamantes","trebol","corazones"};
    public static final String[] DIFIC2={"picas","corazones","picas","corazones","picas","corazones","picas","corazones"};
    public static final String[] DIFIC1={"picas","picas","picas","picas","picas","picas","picas","picas"};

    public Monstruo(int numero, String dificultad,boolean oculta, int posx, int posy) {
        this.numero = numero;
        this.dificultad = dificultad;
        this.oculta= oculta;
    }

    public Monstruo(int numero, String dificultad, float posx, float posy, Boolean oculta) {
        this.numero = numero;
        this.dificultad = dificultad;
        this.posx = posx;
        this.posy = posy;
        this.oculta = oculta;
        
    }

    public Monstruo(int numero, String dificultad, float posx, float posy, int tamx, int tamy, Boolean oculta) {
        this.numero = numero;
        this.dificultad = dificultad;
        this.posx = posx;
        this.posy = posy;
        this.tamx = tamx;
        this.tamy = tamy;
        this.oculta = oculta;
    
    }

    Monstruo() {
    }

    public void girarMonstruo(Monstruo c){
    if(this.oculta == true){
        Image i= new Image(imagenMonstruo+c.getDificultad()+"/"+c.getDificultad()+c.getNumero()+".png");
        c.setImage(i);
        oculta = false;
    }
        
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
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

    public Boolean getOculta() {
        return oculta;
    }

    public void setOculta(Boolean oculta) {
        this.oculta = oculta;
    }
    
    
    public void getLadoMonstruo(){
    if(oculta == true){
        
    if(reverso == 1){
        URL = imagenMonstruo1;
    }else if(reverso== 2){
         URL = imagenMonstruo2;
    }else if(reverso== 3){
         URL = imagenMonstruo3;
    }else if(reverso== 4){
         URL = imagenMonstruo4;
    }
    
    }else if(oculta == false){
     URL = imagenMonstruo;
    
    }
    }
    
    public String getImagenMonstruo() {
        return imagenMonstruo;
    }

    public String getImagenMonstruo1() {
        return imagenMonstruo1;
    }

    public String getImagenMonstruo2() {
        return imagenMonstruo2;
    }

    public String getImagenMonstruo3() {
        return imagenMonstruo3;
    }

    public String getImagenMonstruo4() {
        return imagenMonstruo4;
    }

    @Override
    public String toString() {
        return "Monstruo{" + "numero=" + numero + ", palo=" + dificultad + '}';
    }
    
}
