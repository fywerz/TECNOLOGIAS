/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasUnitarias;

import JPA.controller.JugadorJpaController;
import JPA.controller.exceptions.IllegalOrphanException;
import JPA.controller.exceptions.NonexistentEntityException;
import JPA.entidades.Jugador;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author EricK
 */
public class TestBDJugador {
  JugadorJpaController jugadorjpa=new JugadorJpaController();
  List <Jugador> jugadores=jugadorjpa.findJugadorEntities();
  
  @Before
  public void before(){
    System.out.println("Before");
    jugadores=jugadorjpa.findJugadorEntities();
    
  }
  
  @Test
  public void testJugadorLeer(){
    System.out.println("Leer");
    imprimirJugadores();
    //Asserts que leen el jugador en la base de datos 
    Assert.assertEquals(new Integer(1), jugadores.get(0).getIdJugador());
    Assert.assertEquals("EricKParanoiD", jugadores.get(0).getNombreJugador());
    Assert.assertEquals(true, jugadores.get(0).getGenero());
    Assert.assertEquals(Date.valueOf("1997-08-01"), jugadores.get(0).getFechaNacimiento());
    Assert.assertEquals(0, jugadores.get(0).getPuntaje(), 0);  
  }
  
  @Test
  public void testJugadorEditar(){
    System.out.println("Editar");
    imprimirJugadores();
    //Creacion de objeto Jugador para editar
    Jugador editJ=new Jugador(1, "EricKPrnD", "8fc9c94f022d84cf039b70ae22bc104792906a105462e9765dd6d86aa345d4b0", true, Date.valueOf("1997-08-01"), 0);
    //try con editar Jugador
    try {
      jugadorjpa.edit(editJ);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(TestBDJugador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(TestBDJugador.class.getName()).log(Level.SEVERE, null, ex);
    }
    //Asserts de comprobacion
    jugadores=jugadorjpa.findJugadorEntities();
    Assert.assertEquals(new Integer(1), jugadores.get(0).getIdJugador());
    Assert.assertEquals("EricKPrnD", jugadores.get(0).getNombreJugador());
    Assert.assertEquals(true, jugadores.get(0).getGenero());
    Assert.assertEquals(Date.valueOf("1997-08-01"), jugadores.get(0).getFechaNacimiento());
    Assert.assertEquals(0, jugadores.get(0).getPuntaje(), 0);
    jugadores=jugadorjpa.findJugadorEntities();
    System.out.println("Despues de editar");
    imprimirJugadores();
    //Regreso a la normalidad variables
    //Objeto como antes
    Jugador returnJ=jugadores.get(0);
    returnJ.setNombreJugador("EricKParanoiD");
    //try para regresar el objeto
    try {
      jugadorjpa.edit(returnJ);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(TestBDJugador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(TestBDJugador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @Test
  public void testJugadorCrearEliminar(){
    System.out.println("Crear");
    Jugador crearJ=new Jugador(31, "erick", "Perro", true, Date.valueOf("1996-08-01"), 0);
    jugadorjpa.create(crearJ);
    jugadores=jugadorjpa.findJugadorEntities();
    Assert.assertEquals("erick", jugadores.get(jugadores.size()-1).getNombreJugador());
    Assert.assertEquals(true, jugadores.get(jugadores.size()-1).getGenero());
    Assert.assertEquals(Date.valueOf("1996-08-01"), jugadores.get(jugadores.size()-1).getFechaNacimiento());
    Assert.assertEquals(0, jugadores.get(jugadores.size()-1).getPuntaje(), 0);
    jugadores=jugadorjpa.findJugadorEntities();
    Assert.assertEquals(4, jugadores.size());
    imprimirJugadores();
    System.out.println("Eliminar");
    try {
      jugadorjpa.destroy(jugadores.get(jugadores.size()-1).getIdJugador());
    } catch (IllegalOrphanException | NonexistentEntityException ex) {
      Logger.getLogger(TestBDJugador.class.getName()).log(Level.SEVERE, null, ex);
    }
    jugadores=jugadorjpa.findJugadorEntities();
    Assert.assertEquals(3, jugadores.size());
    System.out.println("Despues de eliminar");
    imprimirJugadores();
   }
  
  
  /**
   * Metodo para imprimir todos los jugadores registrados en el arreglo
   */
  public void imprimirJugadores(){
    for (int i = 0; i < jugadores.size(); i++) {   
      System.out.println("Jugador ID:"+jugadores.get(i).getIdJugador());
      System.out.println("Jugador Nombre:"+jugadores.get(i).getNombreJugador());
      System.out.println("Jugador Contraseña:"+jugadores.get(i).getContrasena());
      System.out.println("Jugador Genero:"+jugadores.get(i).getGenero());
      System.out.println("Jugador FechaNacimiento:"+jugadores.get(i).getFechaNacimiento());
      System.out.println("Jugador Puntaje:"+jugadores.get(i).getPuntaje()+"\n");
  }
  }
}
