package cr.ac.una.defender.service;

import cr.ac.una.defender.model.Game;
import cr.ac.una.defender.model.GameDto;
import cr.ac.una.defender.util.EntityManagerHelper;
import cr.ac.una.defender.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author Carlos
 */
public class PartidaService {
    
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    
    public Respuesta getPartida(Long id) {
        try {
            TypedQuery<Game> query = em.createNamedQuery("Game.findByGamId", Game.class);
            query.setParameter("id", query);
            //JugadorDto jugador = new JugadorDto();

            return new Respuesta(true, "", "", "Partida", new GameDto(query.getSingleResult()));
        } catch (Exception ex) {
            Logger.getLogger(PartidaService.class.getName()).log(Level.SEVERE, "Error obteniendo el partida [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el partida.", "getPartida " + ex.getMessage());
        }
    }
    
    public Respuesta getPartidas(String nombre) {
        try {
            List<GameDto> partidas = new ArrayList<>();
            return new Respuesta(true, "", "", "Partidas", partidas);
        } catch (Exception ex) {
            Logger.getLogger(PartidaService.class.getName()).log(Level.SEVERE, "Error obteniendo partidas.", ex);
            return new Respuesta(false, "Error obteniendo partidas.", "getPartidas " + ex.getMessage());
        }
    }
    
    public Respuesta guardarGame(GameDto gameDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Game game;
            if (gameDto.getId() != null && gameDto.getId() > 0){
                game = em.find(Game.class, gameDto.getId());
                if (game == null){
                    et.rollback();
                    return new Respuesta(false, "No se encrontró la partida a modificar.", "guardarGame NoResultException");
                }
                game.actualizarGame(gameDto);
                game = em.merge(game);
            } else{
                game = new Game(gameDto);
                em.persist(game);
            }
            et.commit();
            return new Respuesta(true, "", "", "Game", new GameDto(game));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(PartidaService.class.getName()).log(Level.SEVERE, "Error guardando la partida.", ex);
            return new Respuesta(false, "Error guardando la partida.", "guardarGame " + ex.getMessage());
        }
    }
    
    public Respuesta eliminarGame(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            Game game;
            if (id != null && id > 0){
                game = em.find(Game.class, id);
                if (game == null){
                    et.rollback();
                    return new Respuesta(false, "No se encrontró el game a eliminar.", "eliminarGame NoResultException");
                }
                em.remove(game);
                et.commit();
            } else{
                et.rollback();
                return new Respuesta(false, "Debe cargar el game a eliminar.", "eliminarGame NoResultException");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, "No se puede eliminar el game porque tiene relaciones con otros registros.", "eliminarGame " + ex.getMessage());
            }
            Logger.getLogger(PartidaService.class.getName()).log(Level.SEVERE, "Error eliminando el game.", ex);
            return new Respuesta(false, "Error eliminando el game.", "eliminarGame " + ex.getMessage());
        }
    }
}
