/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.controller;

import JPA.controller.exceptions.IllegalOrphanException;
import JPA.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import JPA.entidades.Configuraciones;
import JPA.entidades.Jugador;
import JPA.entidades.Partidasganadas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author EricK
 */
public class JugadorJpaController implements Serializable {

  public JugadorJpaController() {
    this.emf = Persistence.createEntityManagerFactory("BuscaMinasPU");
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Jugador jugador) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Configuraciones configuraciones = jugador.getConfiguraciones();
      if (configuraciones != null) {
        configuraciones = em.getReference(configuraciones.getClass(), configuraciones.getJugadoridJugador());
        jugador.setConfiguraciones(configuraciones);
      }
      Partidasganadas partidasganadas = jugador.getPartidasganadas();
      if (partidasganadas != null) {
        partidasganadas = em.getReference(partidasganadas.getClass(), partidasganadas.getJugadoridJugador());
        jugador.setPartidasganadas(partidasganadas);
      }
      em.persist(jugador);
      if (configuraciones != null) {
        Jugador oldJugadorOfConfiguraciones = configuraciones.getJugador();
        if (oldJugadorOfConfiguraciones != null) {
          oldJugadorOfConfiguraciones.setConfiguraciones(null);
          oldJugadorOfConfiguraciones = em.merge(oldJugadorOfConfiguraciones);
        }
        configuraciones.setJugador(jugador);
        configuraciones = em.merge(configuraciones);
      }
      if (partidasganadas != null) {
        Jugador oldJugadorOfPartidasganadas = partidasganadas.getJugador();
        if (oldJugadorOfPartidasganadas != null) {
          oldJugadorOfPartidasganadas.setPartidasganadas(null);
          oldJugadorOfPartidasganadas = em.merge(oldJugadorOfPartidasganadas);
        }
        partidasganadas.setJugador(jugador);
        partidasganadas = em.merge(partidasganadas);
      }
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Jugador jugador) throws IllegalOrphanException, NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Jugador persistentJugador = em.find(Jugador.class, jugador.getIdJugador());
      Configuraciones configuracionesOld = persistentJugador.getConfiguraciones();
      Configuraciones configuracionesNew = jugador.getConfiguraciones();
      Partidasganadas partidasganadasOld = persistentJugador.getPartidasganadas();
      Partidasganadas partidasganadasNew = jugador.getPartidasganadas();
      List<String> illegalOrphanMessages = null;
      if (configuracionesOld != null && !configuracionesOld.equals(configuracionesNew)) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("You must retain Configuraciones " + configuracionesOld + " since its jugador field is not nullable.");
      }
      if (partidasganadasOld != null && !partidasganadasOld.equals(partidasganadasNew)) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("You must retain Partidasganadas " + partidasganadasOld + " since its jugador field is not nullable.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (configuracionesNew != null) {
        configuracionesNew = em.getReference(configuracionesNew.getClass(), configuracionesNew.getJugadoridJugador());
        jugador.setConfiguraciones(configuracionesNew);
      }
      if (partidasganadasNew != null) {
        partidasganadasNew = em.getReference(partidasganadasNew.getClass(), partidasganadasNew.getJugadoridJugador());
        jugador.setPartidasganadas(partidasganadasNew);
      }
      jugador = em.merge(jugador);
      if (configuracionesNew != null && !configuracionesNew.equals(configuracionesOld)) {
        Jugador oldJugadorOfConfiguraciones = configuracionesNew.getJugador();
        if (oldJugadorOfConfiguraciones != null) {
          oldJugadorOfConfiguraciones.setConfiguraciones(null);
          oldJugadorOfConfiguraciones = em.merge(oldJugadorOfConfiguraciones);
        }
        configuracionesNew.setJugador(jugador);
        configuracionesNew = em.merge(configuracionesNew);
      }
      if (partidasganadasNew != null && !partidasganadasNew.equals(partidasganadasOld)) {
        Jugador oldJugadorOfPartidasganadas = partidasganadasNew.getJugador();
        if (oldJugadorOfPartidasganadas != null) {
          oldJugadorOfPartidasganadas.setPartidasganadas(null);
          oldJugadorOfPartidasganadas = em.merge(oldJugadorOfPartidasganadas);
        }
        partidasganadasNew.setJugador(jugador);
        partidasganadasNew = em.merge(partidasganadasNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = jugador.getIdJugador();
        if (findJugador(id) == null) {
          throw new NonexistentEntityException("The jugador with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Jugador jugador;
      try {
        jugador = em.getReference(Jugador.class, id);
        jugador.getIdJugador();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The jugador with id " + id + " no longer exists.", enfe);
      }
      List<String> illegalOrphanMessages = null;
      Configuraciones configuracionesOrphanCheck = jugador.getConfiguraciones();
      if (configuracionesOrphanCheck != null) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Jugador (" + jugador + ") cannot be destroyed since the Configuraciones " + configuracionesOrphanCheck + " in its configuraciones field has a non-nullable jugador field.");
      }
      Partidasganadas partidasganadasOrphanCheck = jugador.getPartidasganadas();
      if (partidasganadasOrphanCheck != null) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("This Jugador (" + jugador + ") cannot be destroyed since the Partidasganadas " + partidasganadasOrphanCheck + " in its partidasganadas field has a non-nullable jugador field.");
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      em.remove(jugador);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Jugador> findJugadorEntities() {
    return findJugadorEntities(true, -1, -1);
  }

  public List<Jugador> findJugadorEntities(int maxResults, int firstResult) {
    return findJugadorEntities(false, maxResults, firstResult);
  }

  private List<Jugador> findJugadorEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Jugador.class));
      Query q = em.createQuery(cq);
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Jugador findJugador(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Jugador.class, id);
    } finally {
      em.close();
    }
  }

  public int getJugadorCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Jugador> rt = cq.from(Jugador.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
