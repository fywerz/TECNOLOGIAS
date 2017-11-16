/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EricK
 */
@Entity
@Table(name = "jugador")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j"),
  @NamedQuery(name = "Jugador.findByNombreJugador", query = "SELECT j FROM Jugador j WHERE j.nombreJugador = :nombreJugador"),
  @NamedQuery(name = "Jugador.findByContrasena", query = "SELECT j FROM Jugador j WHERE j.contrasena = :contrasena"),
  @NamedQuery(name = "Jugador.findByGenero", query = "SELECT j FROM Jugador j WHERE j.genero = :genero"),
  @NamedQuery(name = "Jugador.findByFechaNacimiento", query = "SELECT j FROM Jugador j WHERE j.fechaNacimiento = :fechaNacimiento"),
  @NamedQuery(name = "Jugador.findByIdJugador", query = "SELECT j FROM Jugador j WHERE j.idJugador = :idJugador")})
public class Jugador implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "nombreJugador")
  private String nombreJugador;
  @Basic(optional = false)
  @Column(name = "contrasena")
  private String contrasena;
  @Basic(optional = false)
  @Column(name = "genero")
  private boolean genero;
  @Basic(optional = false)
  @Column(name = "fechaNacimiento")
  @Temporal(TemporalType.DATE)
  private Date fechaNacimiento;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "idJugador")
  private Integer idJugador;
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "jugador")
  private Configuraciones configuraciones;
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "jugador")
  private Partidasganadas partidasganadas;

  public Jugador() {
  }

  public Jugador(Integer idJugador) {
    this.idJugador = idJugador;
  }

  public Jugador(Integer idJugador, String nombreJugador, String contrasena, boolean genero, Date fechaNacimiento) {
    this.idJugador = idJugador;
    this.nombreJugador = nombreJugador;
    this.contrasena = contrasena;
    this.genero = genero;
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getNombreJugador() {
    return nombreJugador;
  }

  public void setNombreJugador(String nombreJugador) {
    this.nombreJugador = nombreJugador;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }

  public boolean getGenero() {
    return genero;
  }

  public void setGenero(boolean genero) {
    this.genero = genero;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public Integer getIdJugador() {
    return idJugador;
  }

  public void setIdJugador(Integer idJugador) {
    this.idJugador = idJugador;
  }

  public Configuraciones getConfiguraciones() {
    return configuraciones;
  }

  public void setConfiguraciones(Configuraciones configuraciones) {
    this.configuraciones = configuraciones;
  }

  public Partidasganadas getPartidasganadas() {
    return partidasganadas;
  }

  public void setPartidasganadas(Partidasganadas partidasganadas) {
    this.partidasganadas = partidasganadas;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idJugador != null ? idJugador.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Jugador)) {
      return false;
    }
    Jugador other = (Jugador) object;
    if ((this.idJugador == null && other.idJugador != null) || (this.idJugador != null && !this.idJugador.equals(other.idJugador))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "JPA.entidades.Jugador[ idJugador=" + idJugador + " ]";
  }
  
}
