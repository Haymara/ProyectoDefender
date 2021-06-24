/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Haymara
 */
public final class Juego extends Pane {
    
    private int puntuacion=1;
    public final TipoMonstruo monstruoss= new  TipoMonstruo(1,1);
    private BorderPane bp = new BorderPane();
    private HBox hb = new HBox();
    private HBox hb1 = new HBox();
    private HBox hfire = new HBox();
    private HBox hfire2 = new HBox();
    private VBox hbz = new VBox();
    private VBox vfire = new VBox();
    private AnchorPane anchoPane = new AnchorPane();
    private AnchorPane anpzona = new AnchorPane();
    public GridPane gp = new GridPane();
    private int contador=0; 
    private int contador2=0;
    
    public int colSource;
    public int filSource;
    
    public Monstruo source= new Monstruo();
    public Monstruo target= new Monstruo();
    public Monstruo seleccionada= new Monstruo();
    
    Random rnd = new Random();
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    
    private boolean bandera=false;
    
    
    public List<List<Monstruo>> monstruosFila = new ArrayList<>();
    private List<StackPane> Pila = new ArrayList();
    private int movimientos=0;
    
    Label pts,movs;
    
    
    public Juego(){};
   public Juego(Label pts, Label movs){
       this.pts=pts;
       this.movs=movs;

        addPila();
        
        crearFilas();
        crearCastillo();
        crearBallesta();
        fire();
        crearZona();
        
    }

   private int sum=0;
   
   EventHandler<MouseEvent> monstruossOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
       @Override
       public void handle(MouseEvent t) {
//  t.setDragDetect(true);
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((GridPane)(t.getSource())).getTranslateX();
            orgTranslateY = ((GridPane)(t.getSource())).getTranslateY();
        }
    };
   
   EventHandler<MouseEvent> monstruoOnMouseReleasedEventHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent t) {  
    }
 };
   
   EventHandler<MouseEvent> monstruossOnMouseDraggedEventHandler =  new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            ((Monstruo)(t.getSource())).setTranslateX(newTranslateX);
            ((Monstruo)(t.getSource())).setTranslateY(newTranslateY);
        }
    };
   
   public List<StackPane> sp ;
   
   public void addPila(){
        int posx=200;
        int posy=100;
        hb.setLayoutX(this.getPrefHeight());
        hb.setLayoutY(this.getPrefWidth());
        hb.setPadding(new Insets(0, 0, 0, 150));
      
     //   hb.setPrefSize(0,0);
      //  bp.setTop(hb);
        hb1.setLayoutX(this.getPrefHeight());
        hb1.setLayoutY(this.getPrefWidth());
        hb1.setPadding(new Insets(230, 0, 0, 10));//posicion de la ballesta
        hb1.setPrefSize(110,70);
       
        hfire.setLayoutX(this.getPrefHeight());
        hfire.setLayoutY(this.getPrefWidth());
        hfire.setPadding(new Insets(178, 0, 0, 190));//posicion de poder de fuego
        
        hfire2.setLayoutX(this.getPrefHeight());
        hfire2.setLayoutY(this.getPrefWidth());
        hfire2.setPadding(new Insets(188, 0, 0, 200));//posicion de poder de fuego
     
        bp.setTop(hb1);
        bp.setBottom(hfire); 
        hbz.setLayoutX(this.getPrefHeight());
        hbz.setLayoutY(this.getPrefWidth());
        hbz.setPadding(new Insets(0, 0, 0, 135));//posicion de la zona
        
        
        this.getChildren().add(anchoPane);
        this.getChildren().add(hbz);  
       
        this.getChildren().add(bp);
         this.getChildren().add(hfire2);
        this.getChildren().add(hb1);
       
//  bp.setBottom(monstruoss);


     monstruoss.monstruos[monstruoss.monstruos.length-1].setOnMouseClicked(event -> MoverAListas());
        bp.setCenter(monstruoss);
     
      bp.setPadding(new Insets(250, 0, 0, 300));
//  monstruoss.set;monstruoss.setLayoutY(10);
          
   }
   
   private void crearBallesta(){
       Image ballesta = new Image("cr/ac/una/defender/resources/BallestaAzul.png");
       
        ImageView bow = new ImageView(ballesta);
        bow.setPreserveRatio(false);
        bow.setFitHeight(120);
        bow.setFitWidth(90);
        hb1.setAlignment(Pos.CENTER);
        hb1.getChildren().add(bow);
        
        hb1.addEventFilter(MouseEvent.MOUSE_DRAGGED, e->{
        bow.setRotate(e.getSceneX());
        bow.setRotate(e.getSceneY());
         });
    
    }
   
      private void fire(){
       Image fire = new Image("cr/ac/una/defender/resources/game/fire1.png");  
     
       ImageView firepower = new ImageView(fire);
 
       firepower.setPreserveRatio(false);
       firepower.setFitHeight(180);
       firepower.setFitWidth(180);
       
        hfire.setAlignment(Pos.CENTER);
        hfire.getChildren().clear();
        hfire2.getChildren().clear();
        hfire.getChildren().add(firepower);
        
        
        hfire.setOnMouseClicked(event -> cancel());
       
    }
      
      public void cancel(){
       Image cancel = new Image("cr/ac/una/defender/resources/game/cancel.png");
       ImageView cancelar = new ImageView(cancel);
       cancelar.setFitHeight(75);
       cancelar.setFitWidth(75);
        hfire.getChildren().clear();
        hfire.getChildren().add(cancelar);
        
        Image fire2 = new Image("cr/ac/una/defender/resources/game/fire2.png");      
        ImageView firepower2 = new ImageView(fire2);
        firepower2.setPreserveRatio(false);
        firepower2.setFitHeight(120);
        firepower2.setFitWidth(120);
        hfire2.getChildren().clear();
        hfire2.getChildren().add(firepower2);
        hfire.setOnMouseClicked(event -> fire());
        //Coloca una mano sobre la carta por la cual pase el ratón
        firepower2.setCursor(Cursor.HAND);
         bp.addEventFilter(MouseEvent.MOUSE_DRAGGED, e->{
         hfire2.setLayoutX(e.getSceneX());
         hfire2.setLayoutY(e.getSceneY());
         });
      }
  
   
    private void crearCastillo(){
        Image castillo = new Image("cr/ac/una/defender/resources/game/Castillo.png");
       
        ImageView castle = new ImageView(castillo);
        castle.setPreserveRatio(false);  
        castle.setFitHeight(580);
        castle.setFitWidth(134);
        anchoPane.getChildren().add(castle);   
         
    }
    
      private void crearZona(){ 
         Image fondoZona = new Image("cr/ac/una/defender/resources/game/FondoP.jpg");
       
        ImageView zone = new ImageView(fondoZona);
        zone.setPreserveRatio(false);
        zone.setFitHeight(581);
        zone.setFitWidth(750);
        hbz.setAlignment(Pos.CENTER);
        hbz.getChildren().add(zone);
    }
   
   private void crearFilas(){
    for(int i=0;i<10;i++){
        monstruosFila.add(new ArrayList<Monstruo>());
    }
    }
   
   private static TranslateTransition tt;
   
   private List<Monstruo> marcoFila = new ArrayList();
   
   public void MoverAListas(){
        AnchorPane p= new AnchorPane();
        p.setBorder(Border.EMPTY);
        p.getChildren().add(gp);
         bp.setCenter(p);
         
         RowConstraints r1= new RowConstraints();
         r1.setValignment(VPos.CENTER);
        gp.getRowConstraints().add(r1);
         
        for (int i=0;i<monstruoss.monstruos.length;i++){
       
       /*
        if(col==10){
        col=0;
        
        fila=fila+1;
        posx=10;
        posy=posy+60;
        gp.setHgap(30);//20
        gp.setVgap(-75);
       }*/
        if( i < 3 ){//cantidad de monstruos
            
            
        monstruoss.addImage(i, true, 3);
      
        
         monstruosFila.get(col).add(monstruoss.siguienteMonstruo());
          double x,y;
         x=monstruoss.monstruos[i].getTranslateX()+600;
         y=monstruoss.monstruos[i].getTranslateY()+120*fila+fila*90;
        
         transicionMonstruos(monstruoss.monstruos[i],x,y,3);//transicion de las monstruos boca abajo
        
         gp.add(monstruoss.monstruos[i], col, fila, 1, 1);
        
         col++;
         posx=posx+0; 
        contador ++;

        } 
          }

   }
    
   
     Random rand = new Random();
   private void transicionMonstruos(Node node,double fromX,double fromY,int sec){
        tt= new TranslateTransition();
        tt.setNode(node);
        tt.setDuration(Duration.seconds(sec));
        tt.setFromX(fromX);
        tt.setFromY(rnd.nextDouble());
        tt.setCycleCount(1);
        tt.setToX(-185);
        tt.setToY(node.getTranslateY());
        tt.play();
   }

 
        private int col=0;
        private  int fila=0;
        private int posx=100;
        private int posy=200;
        
     public void nextRound(){
       
    }
     

public void actualizarPuntuacion(boolean suma){
if(!suma){
    this.puntuacion=puntuacion-1;
    this.movimientos=movimientos+1;
    pts.setText(String.valueOf(puntuacion));
    movs.setText(String.valueOf(movimientos));
    
}else{
    if(suma){
    this.puntuacion=puntuacion+100;
    pts.setText(String.valueOf(puntuacion));
    }}
}

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }


}