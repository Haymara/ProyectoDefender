/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duwan
 */
@Entity
@Table(name = "DEF_JUGADOR", schema = "una")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j"),
    @NamedQuery(name = "Jugador.findByJugId", query = "SELECT j FROM Jugador j WHERE j.id = :id"),
    @NamedQuery(name = "Jugador.findByJugNombreusuario", query = "SELECT j FROM Jugador j WHERE j.nombreusuario = :nombreusuario"),
    /*@NamedQuery(name = "Jugador.findByJugNivel", query = "SELECT j FROM Jugador j WHERE j.nivel = :nivel"),
    @NamedQuery(name = "Jugador.findByJugBallesta", query = "SELECT j FROM Jugador j WHERE j.ballesta = :ballesta"),
    @NamedQuery(name = "Jugador.findByJugVersion", query = "SELECT j FROM Jugador j WHERE j.version = :version")*/})
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "DEF_JUGADOR_JUG_ID_GENERATOR", sequenceName = "una.DEF_JUGADOR_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEF_JUGADOR_JUG_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "JUG_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "JUG_NOMBREUSUARIO")
    private String nombreusuario;
    @Basic(optional = false)
    @Column(name = "JUG_NIVEL")
    private Long nivel;
    @Lob
    @Column(name = "JUG_AVATAR")
    private Serializable avatar;
    @Column(name = "JUG_BALLESTA")
    private String ballesta;
    @Version
    @Basic(optional = false)
    @Column(name = "JUG_VERSION")
    private Long version;
    @OneToOne(mappedBy = "gamIdjugador", fetch = FetchType.LAZY)
    private Game game;

    public Jugador() {
    }

    public Jugador(Long jugId) {
        this.id = jugId;
    }

    public Jugador(Long jugId, String jugNombreusuario, Long jugNivel) {
        this.id = jugId;
        this.nombreusuario = jugNombreusuario;
        this.nivel = jugNivel;
    }
    
    public Jugador(JugadorDto jugadorDto){
       this.nombreusuario = jugadorDto.getNombreUsuario();
       actualizarJugador(jugadorDto);
    }
    
    public void actualizarJugador(JugadorDto jugadorDto){
        this.nombreusuario = jugadorDto.getNombreUsuario();
        this.ballesta = jugadorDto.getBallesta();
        this.nivel = jugadorDto.getNivel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public Serializable getAvatar() {
        return avatar;
    }

    public void setAvatar(Serializable avatar) {
        this.avatar = avatar;
    }

    public String getBallesta() {
        return ballesta;
    }

    public void setBallesta(String ballesta) {
        this.ballesta = ballesta;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.defender.model.Jugador[ jugId=" + id + " ]";
    }  
}