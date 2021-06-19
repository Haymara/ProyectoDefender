/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.defender.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "DEF_GAME", schema = "una")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g"),
    @NamedQuery(name = "Game.findByGamId", query = "SELECT g FROM Game g WHERE g.id = :id"),
    /*@NamedQuery(name = "Game.findByGamPuntaje", query = "SELECT g FROM Game g WHERE g.puntaje = :puntaje"),
    @NamedQuery(name = "Game.findByGamLocalDateinit", query = "SELECT g FROM Game g WHERE g.dateinit = :dateinit"),
    @NamedQuery(name = "Game.findByGamLocalDatelast", query = "SELECT g FROM Game g WHERE g.datelast = :datelast"),
    @NamedQuery(name = "Game.findByGamDuracion", query = "SELECT g FROM Game g WHERE g.duracion = :duracion"),
    @NamedQuery(name = "Game.findByGamKills", query = "SELECT g FROM Game g WHERE g.kills = :kills"),
    @NamedQuery(name = "Game.findByGamSaludcastillo", query = "SELECT g FROM Game g WHERE g.saludcastillo = :saludcastillo"),
    @NamedQuery(name = "Game.findByGamElixir", query = "SELECT g FROM Game g WHERE g.elixir = :elixir"),
    @NamedQuery(name = "Game.findByGamBallestaad", query = "SELECT g FROM Game g WHERE g.ballestaAd = :ballestaAd"),
    @NamedQuery(name = "Game.findByGamBallestacd", query = "SELECT g FROM Game g WHERE g.ballestaCd = :ballestaCd"),
    @NamedQuery(name = "Game.findByGamVersion", query = "SELECT g FROM Game g WHERE g.version = :version")*/})
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "DEF_GAME_GAM_ID_GENERATOR", sequenceName = "una.DEF_GAME_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEF_GAME_GAM_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "GAM_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "GAM_PUNTAJE")
    private Long puntaje;
    @Column(name = "GAM_DATEINIT")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate dateinit;
    @Column(name = "GAM_DATELAST")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate datelast;
    @Basic(optional = false)
    @Column(name = "GAM_DURACION")
    private Long duracion;
    @Column(name = "GAM_KILLS")
    private Long kills;
    @Basic(optional = false)
    @Column(name = "GAM_SALUDCASTILLO")
    private Long saludcastillo;
    @Basic(optional = false)
    @Column(name = "GAM_ELIXIR")
    private Long elixir;
    @Lob
    @Column(name = "GAM_BACKUP")
    private Serializable backup;
    @Basic(optional = false)
    @Column(name = "GAM_BALLESTAAD")
    private Long ballestaAd;
    @Basic(optional = false)
    @Column(name = "GAM_BALLESTACD")
    private Long ballestaCd;
    @Version
    @Basic(optional = false)
    @Column(name = "GAM_VERSION")
    private Long version;
    @JoinColumn(name = "GAM_IDJUGADOR", referencedColumnName = "JUG_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Jugador gamIdjugador;

    public Game() {
    }

    public Game(Long gamId) {
        this.id = gamId;
    }

    public Game(Long gamId, Long gamPuntaje, Long gamDuracion, Long gamSaludcastillo, Long gamElixir, Long gamBallestaad, Long gamBallestacd) {
        this.id = gamId;
        this.puntaje = gamPuntaje;
        this.duracion = gamDuracion;
        this.saludcastillo = gamSaludcastillo;
        this.elixir = gamElixir;
        this.ballestaAd = gamBallestaad;
        this.ballestaCd = gamBallestacd;
    }
    
    public Game(GameDto gameDto){
        actualizarGame(gameDto);
    }
    
    public void actualizarGame(GameDto gameDto){
        this.puntaje = gameDto.getPuntaje();
        this.duracion = gameDto.getDuracion();
        this.saludcastillo = gameDto.getSaludCastillo();
        this.ballestaAd = gameDto.getBallestaAd();
        this.ballestaCd = gameDto.getBallestaCd();
        this.elixir = gameDto.getElixir();
        this.kills = gameDto.getKills();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Long puntaje) {
        this.puntaje = puntaje;
    }

    public LocalDate getLocalDateinit() {
        return dateinit;
    }

    public void setLocalDateinit(LocalDate dateinit) {
        this.dateinit = dateinit;
    }

    public LocalDate getLocalDatelast() {
        return datelast;
    }

    public void setLocalDatelast(LocalDate datelast) {
        this.datelast = datelast;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    public Long getKills() {
        return kills;
    }

    public void setKills(Long kills) {
        this.kills = kills;
    }

    public Long getSaludcastillo() {
        return saludcastillo;
    }

    public void setSaludcastillo(Long saludcastillo) {
        this.saludcastillo = saludcastillo;
    }

    public Long getElixir() {
        return elixir;
    }

    public void setElixir(Long elixir) {
        this.elixir = elixir;
    }

    public Serializable getBackup() {
        return backup;
    }

    public void setBackup(Serializable backup) {
        this.backup = backup;
    }

    public Long getBallestaAd() {
        return ballestaAd;
    }

    public void setBallestaAd(Long ballestaAd) {
        this.ballestaAd = ballestaAd;
    }

    public Long getBallestaCd() {
        return ballestaCd;
    }

    public void setBallestaCd(Long ballestaCd) {
        this.ballestaCd = ballestaCd;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Jugador getGamIdjugador() {
        return gamIdjugador;
    }

    public void setGamIdjugador(Jugador gamIdjugador) {
        this.gamIdjugador = gamIdjugador;
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
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.defender.model.Game[ gamId=" + id + " ]";
    }
}
