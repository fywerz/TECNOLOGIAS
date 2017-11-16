/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EricK
 */
@Entity
@Table(name = "partidasganadas")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Partidasganadas.findAll", query = "SELECT p FROM Partidasganadas p"),
  @NamedQuery(name = "Partidasganadas.findByFacil", query = "SELECT p FROM Partidasganadas p WHERE p.facil = :facil"),
  @NamedQuery(name = "Partidasganadas.findByIntermedio", query = "SELECT p FROM Partidasganadas p WHERE p.intermedio = :intermedio"),
  @NamedQuery(name = "Partidasganadas.findByDificil", query = "SELECT p FROM Partidasganadas p WHERE p.dificil = :dificil"),
  @NamedQuery(name = "Partidasganadas.findByJugadoridJugador", query = "SELECT p FROM Partidasganadas p WHERE p.jugadoridJugador = :jugadoridJugador")})
public class Partidasganadas implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "facil")
  private double facil;
  @Basic(optional = false)
  @Column(name = "intermedio")
  private double intermedio;
  @Basic(optional = false)
  @Column(name = "dificil")
  private double dificil;
  @Id
  @Basic(optional = false)
  @Column(name = "jugador_idJugador")
  private Integer jugadoridJugador;
  @JoinColumn(name = "jugador_idJugador", referencedColumnName = "idJugador", insertable = false, updatable = false)
  @OneToOne(optional = false)
  private Jugador jugador;

  public Partidasganadas() {
  }

  public Partidasganadas(Integer jugadoridJugador) {
    this.jugadoridJugador = jugadoridJugador;
  }

  public Partidasganadas(Integer jugadoridJugador, double facil, double intermedio, double dificil) {
    this.jugadoridJugador = jugadoridJugador;
    this.facil = facil;
    this.intermedio = intermedio;
    this.dificil = dificil;
  }

  public double getFacil() {
    return facil;
  }

  public void setFacil(double facil) {
    this.facil = facil;
  }

  public double getIntermedio() {
    return intermedio;
  }

  public void setIntermedio(double intermedio) {
    this.intermedio = intermedio;
  }

  public double getDificil() {
    return dificil;
  }

  public void setDificil(double dificil) {
    this.dificil = dificil;
  }

  public Integer getJugadoridJugador() {
    return jugadoridJugador;
  }

  public void setJugadoridJugador(Integer jugadoridJugador) {
    this.jugadoridJugador = jugadoridJugador;
  }

  public Jugador getJugador() {
    return jugador;
  }

  public void setJugador(Jugador jugador) {
    this.jugador = jugador;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (jugadoridJugador != null ? jugadoridJugador.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Partidasganadas)) {
      return false;
    }
    Partidasganadas other = (Partidasganadas) object;
    if ((this.jugadoridJugador == null && other.jugadoridJugador != null) || (this.jugadoridJugador != null && !this.jugadoridJugador.equals(other.jugadoridJugador))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "JPA.entidades.Partidasganadas[ jugadoridJugador=" + jugadoridJugador + " ]";
  }
  
}
