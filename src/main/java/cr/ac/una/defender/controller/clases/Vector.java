/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.controller.clases;

/**
 *
 * @author Haymara
 */
public class Vector {
    public double x;
    public double y;
    
    public Vector(){
        this.set(0, 0);
    }
     public Vector(double x, double y){
        this.set(x,y);
    }
    
   public void set(double x, double y){
       this.x = x;
       this.y = y;
   }
   
    public void add(double dx, double dy){
       this.x = dx;
       this.y = dy;
   }
    
    public void multi(double m){
        this.x *= m;
        this.y *= m;
    }
    
    public double getLength(){//para calcular la longitud de un vector
        return Math.sqrt(this.x * this.x +this.y * this.y);
    }
    
}
