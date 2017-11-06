/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.controller;

import JPA.controller.exceptions.IllegalOrphanException;
import JPA.controller.exceptions.NonexistentEntityException;
import JPA.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class PartidasganadasJpaController implements Serializable {

  public PartidasganadasJpaController() {
    this.emf = Persistence.createEntityManagerFactory("BuscaMinasPU");
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Partidasganadas partidasganadas) throws IllegalOrphanException, PreexistingEntityException, Exception {
    List<String> illegalOrphanMessages = null;
    Jugador jugadorOrphanCheck = partidasganadas.getJugador();
    if (jugadorOrphanCheck != null) {
      Partidasganadas oldPartidasganadasOfJugador = jugadorOrphanCheck.getPartidasganadas();
      if (oldPartidasganadasOfJugador != null) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("The Jugador " + jugadorOrphanCheck + " already has an item of type Partidasganadas whose jugador column cannot be null. Please make another selection for the jugador field.");
      }
    }
    if (illegalOrphanMessages != null) {
      throw new IllegalOrphanException(illegalOrphanMessages);
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Jugador jugador = partidasganadas.getJugador();
      if (jugador != null) {
        jugador = em.getReference(jugador.getClass(), jugador.getIdJugador());
        partidasganadas.setJugador(jugador);
      }
      em.persist(partidasganadas);
      if (jugador != null) {
        jugador.setPartidasganadas(partidasganadas);
        jugador = em.merge(jugador);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      if (findPartidasganadas(partidasganadas.getJugadoridJugador()) != null) {
        throw new PreexistingEntityException("Partidasganadas " + partidasganadas + " already exists.", ex);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Partidasganadas partidasganadas) throws IllegalOrphanException, NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Partidasganadas persistentPartidasganadas = em.find(Partidasganadas.class, partidasganadas.getJugadoridJugador());
      Jugador jugadorOld = persistentPartidasganadas.getJugador();
      Jugador jugadorNew = partidasganadas.getJugador();
      List<String> illegalOrphanMessages = null;
      if (jugadorNew != null && !jugadorNew.equals(jugadorOld)) {
        Partidasganadas oldPartidasganadasOfJugador = jugadorNew.getPartidasganadas();
        if (oldPartidasganadasOfJugador != null) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("The Jugador " + jugadorNew + " already has an item of type Partidasganadas whose jugador column cannot be null. Please make another selection for the jugador field.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (jugadorNew != null) {
        jugadorNew = em.getReference(jugadorNew.getClass(), jugadorNew.getIdJugador());
        partidasganadas.setJugador(jugadorNew);
      }
      partidasganadas = em.merge(partidasganadas);
      if (jugadorOld != null && !jugadorOld.equals(jugadorNew)) {
        jugadorOld.setPartidasganadas(null);
        jugadorOld = em.merge(jugadorOld);
      }
      if (jugadorNew != null && !jugadorNew.equals(jugadorOld)) {
        jugadorNew.setPartidasganadas(partidasganadas);
        jugadorNew = em.merge(jugadorNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = partidasganadas.getJugadoridJugador();
        if (findPartidasganadas(id) == null) {
          throw new NonexistentEntityException("The partidasganadas with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Partidasganadas partidasganadas;
      try {
        partidasganadas = em.getReference(Partidasganadas.class, id);
        partidasganadas.getJugadoridJugador();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The partidasganadas with id " + id + " no longer exists.", enfe);
      }
      Jugador jugador = partidasganadas.getJugador();
      if (jugador != null) {
        jugador.setPartidasganadas(null);
        jugador = em.merge(jugador);
      }
      em.remove(partidasganadas);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Partidasganadas> findPartidasganadasEntities() {
    return findPartidasganadasEntities(true, -1, -1);
  }

  public List<Partidasganadas> findPartidasganadasEntities(int maxResults, int firstResult) {
    return findPartidasganadasEntities(false, maxResults, firstResult);
  }

  private List<Partidasganadas> findPartidasganadasEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Partidasganadas.class));
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

  public Partidasganadas findPartidasganadas(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Partidasganadas.class, id);
    } finally {
      em.close();
    }
  }

  public int getPartidasganadasCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Partidasganadas> rt = cq.from(Partidasganadas.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
