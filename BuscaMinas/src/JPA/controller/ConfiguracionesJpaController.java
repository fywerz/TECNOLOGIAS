/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA.controller;

import JPA.controller.exceptions.IllegalOrphanException;
import JPA.controller.exceptions.NonexistentEntityException;
import JPA.controller.exceptions.PreexistingEntityException;
import JPA.entidades.Configuraciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import JPA.entidades.Jugador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EricK
 */
public class ConfiguracionesJpaController implements Serializable {

  public ConfiguracionesJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Configuraciones configuraciones) throws IllegalOrphanException, PreexistingEntityException, Exception {
    List<String> illegalOrphanMessages = null;
    Jugador jugadorOrphanCheck = configuraciones.getJugador();
    if (jugadorOrphanCheck != null) {
      Configuraciones oldConfiguracionesOfJugador = jugadorOrphanCheck.getConfiguraciones();
      if (oldConfiguracionesOfJugador != null) {
        if (illegalOrphanMessages == null) {
          illegalOrphanMessages = new ArrayList<String>();
        }
        illegalOrphanMessages.add("The Jugador " + jugadorOrphanCheck + " already has an item of type Configuraciones whose jugador column cannot be null. Please make another selection for the jugador field.");
      }
    }
    if (illegalOrphanMessages != null) {
      throw new IllegalOrphanException(illegalOrphanMessages);
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Jugador jugador = configuraciones.getJugador();
      if (jugador != null) {
        jugador = em.getReference(jugador.getClass(), jugador.getIdJugador());
        configuraciones.setJugador(jugador);
      }
      em.persist(configuraciones);
      if (jugador != null) {
        jugador.setConfiguraciones(configuraciones);
        jugador = em.merge(jugador);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      if (findConfiguraciones(configuraciones.getJugadoridJugador()) != null) {
        throw new PreexistingEntityException("Configuraciones " + configuraciones + " already exists.", ex);
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Configuraciones configuraciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Configuraciones persistentConfiguraciones = em.find(Configuraciones.class, configuraciones.getJugadoridJugador());
      Jugador jugadorOld = persistentConfiguraciones.getJugador();
      Jugador jugadorNew = configuraciones.getJugador();
      List<String> illegalOrphanMessages = null;
      if (jugadorNew != null && !jugadorNew.equals(jugadorOld)) {
        Configuraciones oldConfiguracionesOfJugador = jugadorNew.getConfiguraciones();
        if (oldConfiguracionesOfJugador != null) {
          if (illegalOrphanMessages == null) {
            illegalOrphanMessages = new ArrayList<String>();
          }
          illegalOrphanMessages.add("The Jugador " + jugadorNew + " already has an item of type Configuraciones whose jugador column cannot be null. Please make another selection for the jugador field.");
        }
      }
      if (illegalOrphanMessages != null) {
        throw new IllegalOrphanException(illegalOrphanMessages);
      }
      if (jugadorNew != null) {
        jugadorNew = em.getReference(jugadorNew.getClass(), jugadorNew.getIdJugador());
        configuraciones.setJugador(jugadorNew);
      }
      configuraciones = em.merge(configuraciones);
      if (jugadorOld != null && !jugadorOld.equals(jugadorNew)) {
        jugadorOld.setConfiguraciones(null);
        jugadorOld = em.merge(jugadorOld);
      }
      if (jugadorNew != null && !jugadorNew.equals(jugadorOld)) {
        jugadorNew.setConfiguraciones(configuraciones);
        jugadorNew = em.merge(jugadorNew);
      }
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = configuraciones.getJugadoridJugador();
        if (findConfiguraciones(id) == null) {
          throw new NonexistentEntityException("The configuraciones with id " + id + " no longer exists.");
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
      Configuraciones configuraciones;
      try {
        configuraciones = em.getReference(Configuraciones.class, id);
        configuraciones.getJugadoridJugador();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The configuraciones with id " + id + " no longer exists.", enfe);
      }
      Jugador jugador = configuraciones.getJugador();
      if (jugador != null) {
        jugador.setConfiguraciones(null);
        jugador = em.merge(jugador);
      }
      em.remove(configuraciones);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Configuraciones> findConfiguracionesEntities() {
    return findConfiguracionesEntities(true, -1, -1);
  }

  public List<Configuraciones> findConfiguracionesEntities(int maxResults, int firstResult) {
    return findConfiguracionesEntities(false, maxResults, firstResult);
  }

  private List<Configuraciones> findConfiguracionesEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Configuraciones.class));
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

  public Configuraciones findConfiguraciones(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Configuraciones.class, id);
    } finally {
      em.close();
    }
  }

  public int getConfiguracionesCount() {
    EntityManager em = getEntityManager();
    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      Root<Configuraciones> rt = cq.from(Configuraciones.class);
      cq.select(em.getCriteriaBuilder().count(rt));
      Query q = em.createQuery(cq);
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }
  
}
