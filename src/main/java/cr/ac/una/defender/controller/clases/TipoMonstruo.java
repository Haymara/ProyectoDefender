/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller.clases;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 *
 * @author Haymara
 */
public class TipoMonstruo extends Pane {
 
  public Monstruo monstruos[];
    
  public int posSigMonstruo;
  private int monstruoActual;
  public static final int NUM_MONSTRUOS=104;

  private final int NUMERO_DE_MONSTRUOS = 52;
  private Random numerosAleatorios;
   
  
  public Monstruo[] getMonstruos() {
        return monstruos;
    }
  
 public TipoMonstruo(int numerodificultad, int tipoMonstruo) {
    this.monstruos = new Monstruo[NUM_MONSTRUOS];
      
    this.posSigMonstruo = 0;
        
    crearMonstruos(100,10,numerodificultad,true,tipoMonstruo);
    }
 
    //Este metodo se encarga de crear  104 monstruos
    //Dependiendo de la dificulta, agregara monstruos distintos.
    private void crearMonstruos(int x, int y, int dificultad, boolean oculta, int tipoMonstruoOculto){
//      posSigMonstruo=0;
        String[] dificultadMonstruo = null ;

      if(dificultad == 4){
      dificultadMonstruo = Monstruo.DIFIC3;
      } 
      if(dificultad == 2){
      dificultadMonstruo = Monstruo.DIFIC2;
      }
      if(dificultad == 1){
      dificultadMonstruo = Monstruo.DIFIC1;
      }
      
      //Evaluamos la dificultad
     
        for (int i=0;i<8;i++){
            for(int j=0;j<Monstruo.Limite_Monstruo_Dificultad;j++){ 
                //formula para determinar la posicion en el arreglo
                int pos=((i*(Monstruo.Limite_Monstruo_Dificultad)+j));
                
         // creamos un nuevo monstruo
                
      monstruos[(pos)] = new Monstruo(j+1,dificultadMonstruo[i],oculta,x,y);
               
      
      addImage(pos,oculta,tipoMonstruoOculto);
        //le damos un tamaño al monstruo
      monstruos[pos].setFitWidth(70);
      monstruos[pos].setFitHeight(100);
      monstruos[pos].setPreserveRatio(false);

      monstruos[pos].setLayoutX(x);
      monstruos[pos].setLayoutY(y+1);

        //insertamos la carta en la clase padre "Panel"
       
       this.getChildren().add(monstruos[pos]);
        }
      }
    }
    
    public void addImage(int id, boolean oculta, int tipoCartaOculta){
    String tipoMonstruoOc = null;
     //si está oculta, llamamos el atributo que contiene la direccion de la img oculta
               if(oculta == true){
                   
                   if(tipoCartaOculta == 1){
                        tipoMonstruoOc = Monstruo.imagenMonstruo1;}
                   if(tipoCartaOculta == 2){
                        tipoMonstruoOc = Monstruo.imagenMonstruo2;}
                   if(tipoCartaOculta == 3){
                        tipoMonstruoOc = Monstruo.imagenMonstruo3;}
                   if(tipoCartaOculta == 4){
                        tipoMonstruoOc = Monstruo.imagenMonstruo4;}
                                     
                Image img = new Image(tipoMonstruoOc);     
                //agregamos la imagen al monstruo
                monstruos[id].setImage(img); monstruos[id].setOculta(true);
               } 
               else
                if(oculta == false){
               String im =monstruos[id].getImagenMonstruo()+monstruos[id].getDificultad()+"/"+monstruos[id].getDificultad()+monstruos[id].getNumero()+".png";
               System.out.println(im);
               Image imagen=new Image(im);
               //agregamos la imagen a la carta
               monstruos[id].setImage(imagen);
               monstruos[id].setOculta(false);
              }
    
    
    
    }
    
    public void voltear(int id){
      addImage(id,false,3);    
     }
    
    public void actualizarMonstruos(){
      for(int i=posSigMonstruo;i<monstruos.length;i++){
    
      monstruos[i].getLadoMonstruo();
    } 
  }
    
    public void voltearMonstruo(){
      for(int i=0;i<monstruos.length;i++){
      if(monstruos[i].getOculta() == false){
      monstruos[i].setOculta(Boolean.TRUE);
      }else if(monstruos[i].getOculta() == true){
          monstruos[i].setOculta(Boolean.TRUE);
        }
      }
    }
    
    public void Distribuir(){
        int posx=10;
        int posy=10;
        
        for(int i=0;i<monstruos.length;i++){
        if(i%10==0 && i != 0){
        posy=posy+60;
        posx=10;
        }
        monstruos[i].setLayoutX(posx);
        monstruos[i].setLayoutY(posy);
        posx=posx+75;
    }
        
  }
    
    
    public void Mezclar(){
      int posAleatoria = 0;
      Monstruo c;
      for(int i=0;i<monstruos.length;i++){
      posAleatoria = generarNumAleatorio(0,NUM_MONSTRUOS-1);
      c = monstruos[i];
   
      
      monstruos[i].setLayoutX(monstruos[posAleatoria].getLayoutX());
      monstruos[posAleatoria].setLayoutX(c.getLayoutX());
      monstruos[i].setLayoutY(monstruos[posAleatoria].getLayoutY());
      monstruos[posAleatoria].setLayoutY(c.getLayoutY());
      
      monstruos[i] = monstruos[posAleatoria];
      monstruos[posAleatoria]=c;
      }  
        
    }
    
   
    public void MezclarIv(){
     // Abre metodo Mezclar
        monstruoActual = 0;
 
        for ( int primera = 0; primera < monstruos.length; primera++ ){ // Abre for
          int segunda = numerosAleatorios.nextInt(NUMERO_DE_MONSTRUOS);
 
           Monstruo temp = monstruos[primera];

             monstruos[primera] = monstruos[segunda];
             monstruos[segunda] = temp;
        }  // Cierra for    
    }
    
    
    public Monstruo siguienteMonstruo(){
        Monstruo c= null;
        
        if(posSigMonstruo == NUM_MONSTRUOS){
        
        }else{
        c = monstruos[posSigMonstruo++];
        }
        return c;
    }
    
    //varios monstruos
    public Monstruo[] darMonstruos(int numMonstruos){
    
        if(numMonstruos> NUM_MONSTRUOS){
            System.out.println("No pueden haber mas monstruos de los que hay");
        }else{
        if(monstruosDisponibles()<numMonstruos){
        System.out.println("Error");
        }else{
        Monstruo [] monstruosDar = new Monstruo[numMonstruos];
             
            for(int i=0;i<monstruosDar.length;i++){
            monstruosDar[i]=siguienteMonstruo();
            }
            return monstruosDar;
        }  
      } 
     return null;
    }
    
    
    public int monstruosDisponibles(){
    return NUM_MONSTRUOS-posSigMonstruo;
    }

    
    public void monstruosMonton(){
    if(monstruosDisponibles() == NUM_MONSTRUOS){
    System.out.println("No hay monstruos en el montón");
    }else{
    for(int i=0;i<posSigMonstruo;i++){
    System.out.println(monstruos[i]);
         }
       }
    }
    
    
    public void mostrarMontonMonstruos(){
    if(monstruosDisponibles() == 0){
    System.out.println("No hay monstruos");
    }else{
    for(int i=posSigMonstruo;i<monstruos.length;i++){
    System.out.println(monstruos[i]);
    }
    }
    
    }

    @Override
   public String toString(){
       
        return "TipoMonstruo{" + "monstruos=" + monstruos + ", posSigMonstruo=" + posSigMonstruo + '}';
        
       }

    public void imprimirTipo() {
        System.out.println();
        for (int i=0;i<NUM_MONSTRUOS;i++){
            
            if(i!=0 && i%13 ==0){
                System.out.println();
            }
            System.out.print(monstruos[i]);
        }
        System.out.print(monstruos[103]);
    }

    private int generarNumAleatorio(int minimo, int maximo) {
    int num=(int)Math.floor(Math.random()*(maximo-minimo+1)+(minimo));
       return num;
    }
   

   }

