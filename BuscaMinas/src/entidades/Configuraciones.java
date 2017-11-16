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
@Table(name = "configuraciones")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Configuraciones.findAll", query = "SELECT c FROM Configuraciones c"),
  @NamedQuery(name = "Configuraciones.findByDificultad", query = "SELECT c FROM Configuraciones c WHERE c.dificultad = :dificultad"),
  @NamedQuery(name = "Configuraciones.findByVolumen", query = "SELECT c FROM Configuraciones c WHERE c.volumen = :volumen"),
  @NamedQuery(name = "Configuraciones.findByJugadoridJugador", query = "SELECT c FROM Configuraciones c WHERE c.jugadoridJugador = :jugadoridJugador"),
  @NamedQuery(name = "Configuraciones.findByPuerto", query = "SELECT c FROM Configuraciones c WHERE c.puerto = :puerto"),
  @NamedQuery(name = "Configuraciones.findByIp", query = "SELECT c FROM Configuraciones c WHERE c.ip = :ip")})
public class Configuraciones implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "dificultad")
  private int dificultad;
  @Basic(optional = false)
  @Column(name = "volumen")
  private int volumen;
  @Id
  @Basic(optional = false)
  @Column(name = "jugador_idJugador")
  private Integer jugadoridJugador;
  @Basic(optional = false)
  @Column(name = "puerto")
  private int puerto;
  @Basic(optional = false)
  @Column(name = "ip")
  private String ip;
  @JoinColumn(name = "jugador_idJugador", referencedColumnName = "idJugador", insertable = false, updatable = false)
  @OneToOne(optional = false)
  private Jugador jugador;

  public Configuraciones() {
  }

  public Configuraciones(Integer jugadoridJugador) {
    this.jugadoridJugador = jugadoridJugador;
  }

  public Configuraciones(Integer jugadoridJugador, int dificultad, int volumen, int puerto, String ip) {
    this.jugadoridJugador = jugadoridJugador;
    this.dificultad = dificultad;
    this.volumen = volumen;
    this.puerto = puerto;
    this.ip = ip;
  }

  public int getDificultad() {
    return dificultad;
  }

  public void setDificultad(int dificultad) {
    this.dificultad = dificultad;
  }

  public int getVolumen() {
    return volumen;
  }

  public void setVolumen(int volumen) {
    this.volumen = volumen;
  }

  public Integer getJugadoridJugador() {
    return jugadoridJugador;
  }

  public void setJugadoridJugador(Integer jugadoridJugador) {
    this.jugadoridJugador = jugadoridJugador;
  }

  public int getPuerto() {
    return puerto;
  }

  public void setPuerto(int puerto) {
    this.puerto = puerto;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
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
    if (!(object instanceof Configuraciones)) {
      return false;
    }
    Configuraciones other = (Configuraciones) object;
    if ((this.jugadoridJugador == null && other.jugadoridJugador != null) || (this.jugadoridJugador != null && !this.jugadoridJugador.equals(other.jugadoridJugador))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "JPA.entidades.Configuraciones[ jugadoridJugador=" + jugadoridJugador + " ]";
  }
  
}
