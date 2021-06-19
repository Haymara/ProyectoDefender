/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller.clases;

import cr.ac.una.defender.controller.clases.Monstruo;
import java.awt.Canvas;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Haymara
 */
public class Juego extends Pane {
    
    
    private int puntuacion=500;
    private final String red= "Red.jpg";
    private final String blue= "Blue.jpg";
    private final String green= "Green.jpg";
    private BorderPane bp = new BorderPane();
    private HBox hb = new HBox();
    private VBox hbz = new VBox();
    private AnchorPane anchoPane = new AnchorPane();
    private AnchorPane anpzona = new AnchorPane();
    public GridPane gp = new GridPane();
    private int contador=0; 
    private int contador2=0;
    
    public int colSource;
    public int filSource;
    

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    
    private boolean bandera=false;
    
        Random rnd = new Random();
   private AnchorPane anchoPane1 = new AnchorPane();
    Pane playfieldLayer;
    Pane scoreLayer;

    Image playerImage;
    Image enemyImage;

    
    List<Enemy> enemies = new ArrayList<>();

    Text collisionText = new Text();
    boolean collision = false;

    
    public List<List<Monstruo>> cartasFila = new ArrayList<>();
    public List<List<Monstruo>> cartasPilaWin = new ArrayList<>();
    public List<Monstruo>cartasMazo = new ArrayList(); 
    private List<Monstruo> seleccionadas = new ArrayList();
    private List<Monstruo> roundWin ;
    private List<StackPane> fondosPila = new ArrayList();
    private int movimientos=0;
    
    Label pts,movs;
   
    
    public Juego(){};
   public Juego(Label pts, Label movs){
       this.pts=pts;
       this.movs=movs;

        anchoPane.setPadding(new Insets(0,0,0,0));
        addPila();
        crearCastillo();
        crearBallesta();
        crearZona();
        
      // spawnEnemies( true);
      
    }
   
   
   public List<StackPane> sp ;
   public void addPila(){
        int posx=400;
        int posy=500;
        hb.setLayoutX(this.getPrefHeight());
        hb.setLayoutY(this.getPrefWidth());
        hb.setPadding(new Insets(230, 0, 0, 10));//posicion de la ballesta
        hb.setPrefSize(110,70);
        bp.setTop(hb);
      
        hbz.setLayoutX(this.getPrefHeight());
        hbz.setLayoutY(this.getPrefWidth());
        hbz.setPadding(new Insets(0, 0, 0, 135));//posicion de la zona
        
        
        this.getChildren().add(anchoPane);
        this.getChildren().add(hbz);      
        this.getChildren().add(anchoPane1);
        this.getChildren().add(hb);
        
        // create layers
        playfieldLayer = new Pane();
        scoreLayer = new Pane();

        anchoPane1.getChildren().add( playfieldLayer);
        anchoPane1.getChildren().add( scoreLayer);
     
         loadGame();

        createScoreLayer();
        //createPlayers();

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // player input
                //players.forEach(sprite -> sprite.processInput());dadwd

                // add random enemies
                spawnEnemies( true);

                // movement
               // players.forEach(sprite -> sprite.move());adawda
                enemies.forEach(sprite -> sprite.move());

                // check collisions
                checkCollisions();

                // update sprites in scenes
              //  players.forEach(sprite -> sprite.updateUI());dadwa
                enemies.forEach(sprite -> sprite.updateUI());

                // check if sprite can be removed
                enemies.forEach(sprite -> sprite.checkRemovability());

                // remove removables from list, layer, etc
                removeSprites( enemies);

                // update score, health, etc
                updateScore();
            }

        };
        gameLoop.start();
   }
   //game
   
    private void loadGame() {
       // playerImage = new Image( getClass().getResource("/player.png").toExternalForm());
        enemyImage = new Image( getClass().getResource("/enemy.png").toExternalForm());
   }
      //game


    private void createScoreLayer() {


    }
    
    private void spawnEnemies( boolean random) {

        if( random && rnd.nextInt(Settings.ENEMY_SPAWN_RANDOMNESS) != 0) {
            return;
        }

        // image
        Image image = enemyImage;

        // random speed
        double speed = rnd.nextDouble() * 1.0 + 2.0;

        // x position range: enemy is always fully inside the screen, no part of it is outside
        // y position: right on top of the view, so that it becomes visible with the next game iteration
      //  double x = rnd.nextDouble() * (400 - image.getWidth());
        //double y = -image.getHeight();
 
       // double x = -image.getWidth();
                
        //double y = rnd.nextDouble() * (400 - image.getHeight());
        // create a sprite
        Enemy enemy = new Enemy( anchoPane1, image, 800, 0, 30, 0, speed, 0, 0,1);

        // manage sprite
        enemies.add(enemy);

    }

    private void removeSprites(  List<? extends Sprite> spriteList) {
        Iterator<? extends Sprite> iter = spriteList.iterator();
        while( iter.hasNext()) {
            Sprite sprite = iter.next();

            if( sprite.isRemovable()) {

                // remove from layer
                sprite.removeFromLayer();

                // remove from list
                iter.remove();
            }
        }
    }

    private void checkCollisions() {

        collision = false;
/*
        for( Player player: players) {
            for( Enemy enemy: enemies) {
                if( player.collidesWith(enemy)) {
                    collision = true;
                }
            }
        }*/
    }

    private void updateScore() {
        if( collision) {
            collisionText.setText("Collision");
        } else {
            collisionText.setText("");
        }
    }
    //game
   
   
   private void crearBallesta(){
       Image ballesta = new Image("cr/ac/una/defender/resources/BallestaAzul.png");
       
        ImageView bow = new ImageView(ballesta);
        bow.setPreserveRatio(false);
        bow.setFitHeight(120);
        bow.setFitWidth(90);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(bow);
        
        hb.addEventFilter(MouseEvent.MOUSE_DRAGGED, e->{
        bow.setRotate(e.getSceneX());
        bow.setRotate(e.getSceneY());
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
    
   /*
   
   private static TranslateTransition tt;
   
   private List<Monstruo> marcoFila = new ArrayList();
  
*/
}