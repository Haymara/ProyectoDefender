/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.model;

import javafx.beans.property.SimpleStringProperty;

public class JugadorDto {
    
    public SimpleStringProperty id;
    public SimpleStringProperty nombreUsuario;
    public String ballesta;
    public Long nivel;
    
    
    
    public JugadorDto() {
         this.nombreUsuario = new SimpleStringProperty();
         this.id = new SimpleStringProperty();
    }
     
    public JugadorDto(Jugador jugador){
        this();
        this.nombreUsuario.set(jugador.getNombreusuario());
        this.id.set(jugador.getId().toString()); 
    }

    public Long getId() {
        if (id.get()!=null && !id.get().isEmpty())
            return Long.valueOf(id.get());
        return null;
    }

    public void setId(Long id) {
         this.id.setValue(id.toString());
    }
    
    public String getNombreUsuario() {
        return nombreUsuario.get();
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario.set(nombreUsuario);
    }
    
        public String getBallesta() {
        return ballesta;
    }

    public void setBallesta(String ballesta) {
        this.ballesta = ballesta;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(long nivel) {
        this.nivel = nivel;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JugadorDto{nombreUsuario=").append(nombreUsuario.get());
        sb.append(", id=").append(id.get());
        sb.append(", ballesta=").append(ballesta);
        sb.append(", nivel=").append(nivel);
        sb.append('}');
        return sb.toString();
    }
}
