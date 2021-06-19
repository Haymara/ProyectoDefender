package cr.ac.una.defender.service;

import cr.ac.una.defender.model.Jugador;
import cr.ac.una.defender.model.JugadorDto;
import cr.ac.una.defender.util.Respuesta;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import cr.ac.una.defender.util.EntityManagerHelper;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

public class JugadorService {
    
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    
    public Respuesta getUsuario(Long id) {
        try {      
            TypedQuery<Jugador> query = em.createNamedQuery("Jugador.findByJugNombreusuario", Jugador.class);
            query.setParameter("nombreUsuario", query);
            return new Respuesta(true, "", "", "Jugador", new JugadorDto(query.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe el usuario con las credenciales ingresadas", "getJugador NoResultExeption, para el usuario [" + id + "]");
        }catch (NonUniqueResultException ex) {
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error consultando el usuario", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "NonUniqueResultExeption, para el usuario [" + id + "]");
        } catch (Exception ex) {
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }
    
    public Respuesta getJugador(Long id) {
        try {
            TypedQuery<Jugador> query = em.createNamedQuery("Jugador.findByJugId", Jugador.class);
            query.setParameter("id", query);
            //JugadorDto jugador = new JugadorDto();

            return new Respuesta(true, "", "", "Jugador", new JugadorDto(query.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe el jugador con las credenciales ingresadas", "getJugador NoResultExeption, para el id [" + id + "]");
        }catch (NonUniqueResultException ex) {
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error consultando el jugador", ex);
            return new Respuesta(false, "Error obteniendo el jugador.", "NonUniqueResultExeption, para el id [" + id + "]");
        }catch (Exception ex) {
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error obteniendo el jugador [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el jugador.", "getJugador " + ex.getMessage());
        }
    }
    
    public Respuesta getJugadores(String nombreUsuario) {
        try {
            List<JugadorDto> jugadores = new ArrayList<>();
            return new Respuesta(true, "", "", "Jugadors", jugadores);
        } catch (Exception ex) {
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error obteniendo jugadores.", ex);
            return new Respuesta(false, "Error obteniendo jugadores.", "getJugadores " + ex.getMessage());
        }
    }
    
    public Respuesta guardarJugador(JugadorDto jugadorDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Jugador jugador;
            if(jugadorDto.getId() != null){
                jugador = em.find(Jugador.class, jugadorDto.getId());
                if(jugador == null){
                    et.rollback();
                    return new Respuesta(false, "No se encontró el jugador a modificar", "guardarJugador NoResultExeption");
                }
                jugador.actualizarJugador(jugadorDto);
                jugador = em.merge(jugador);
            } else{
                jugador = new Jugador(jugadorDto);
                em.persist(jugador);
            }
            et.commit();
            return new Respuesta(true, "", "", "Jugador", new JugadorDto(jugador));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error guardando el jugador.", ex);
            return new Respuesta(false, "Error guardando el jugador.", "guardarJugador " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarJugador(String nombreUsuario) {
        try {
            et = em.getTransaction();
            et.begin();
            Jugador jugador;
            if(nombreUsuario != null){
                jugador = em.find(Jugador.class, nombreUsuario);
                if(jugador == null){
                    et.rollback();
                    return new Respuesta(false, "No se encontro el jugador a eliminar", "eliminarJugador NoResultExeption");
                }
                em.remove(jugador);
            } else{
                et.rollback();
                return new Respuesta(false, "Debe cargar el jugador a eliminar", "eliminarJugador NoResultExeption");
            }
            et.commit();
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if(ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class){
                return new Respuesta(false, "No se puede eliminar el jugador porque tiene relaciones con ostros regustros", "eliminarJugador " + ex.getMessage());
            }
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error eliminando el jugador.", ex);
            return new Respuesta(false, "Error eliminando el jugador.", "eliminarJugador " + ex.getMessage());
        }
    }
}
