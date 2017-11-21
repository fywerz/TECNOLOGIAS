/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasUnitarias;

import controller.JugadorJpaController;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entidades.Jugador;
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
  //Inicializacion de variables globales
  JugadorJpaController jugadorjpa=new JugadorJpaController();
  List <Jugador> jugadores=jugadorjpa.findJugadorEntities();
  
  /**
   * Before para las clases que actualiza la lista de Jugadores
   */
  @Before
  public void before(){
    System.out.println("Before");
    jugadores=jugadorjpa.findJugadorEntities();
  }
  
  /**
   * Test para lectura de datos en la base
   */
  @Test
  public void testJugadorLeer(){
    System.out.println("Leer");
    imprimirJugadores(); 
    //Asserts que leen el jugador de la lista y comparan con valores conocidos 
    Assert.assertEquals(Integer.valueOf("1"), jugadores.get(0).getIdJugador());
    Assert.assertEquals("EricKParanoiD", jugadores.get(0).getNombreJugador());
    Assert.assertEquals(true, jugadores.get(0).getGenero());
    Assert.assertEquals(Date.valueOf("1997-08-01"), jugadores.get(0).getFechaNacimiento());
  }
  
  /**
   * Metodo para probar la edicion en base de datos
   */
  @Test
  public void testJugadorEditar(){
    System.out.println("Editar");
    imprimirJugadores();
    //Creacion de objeto Jugador para editar
    Jugador editJ=new Jugador(2, "EricKPrnD", "8fc9c94f022d84cf039b70ae22bc104792906a105462e9765dd6d86aa345d4b0", true, Date.valueOf("1997-08-01"));
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
    Assert.assertEquals(Integer.valueOf("2"), jugadores.get(1).getIdJugador());
    Assert.assertEquals("EricKPrnD", jugadores.get(1).getNombreJugador());
    Assert.assertEquals(true, jugadores.get(1).getGenero());
    Assert.assertEquals(Date.valueOf("1997-08-01"), jugadores.get(1).getFechaNacimiento());
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
  /**
   * Test para la creacion y eliminacion (juntos para no cambiar nada en la base de datos)
   */
  @Test
  public void testJugadorCrearEliminar(){
    System.out.println("Crear");
    //Creacion de entidad a crear
    Jugador crearJ=new Jugador(31, "erick", "Perro", true, Date.valueOf("1996-08-01"));
    jugadorjpa.create(crearJ); //Creacion de la entidad
    jugadores=jugadorjpa.findJugadorEntities(); //Actualizacion de la lista
    //Asserts de comprobacion de nuevo jugador creado (jugadores.size()-1 para comprobar el ultimo jugador creado)
    Assert.assertEquals("erick", jugadores.get(jugadores.size()-1).getNombreJugador());
    Assert.assertEquals(true, jugadores.get(jugadores.size()-1).getGenero());
    Assert.assertEquals(Date.valueOf("1996-08-01"), jugadores.get(jugadores.size()-1).getFechaNacimiento());
    jugadores=jugadorjpa.findJugadorEntities();
    //Comprobacion de numero de jugadores antes de eliminacion
    Assert.assertEquals(3, jugadores.size());
    imprimirJugadores();
    System.out.println("Eliminar");
    try {
      jugadorjpa.destroy(jugadores.get(jugadores.size()-1).getIdJugador());
    } catch (IllegalOrphanException | NonexistentEntityException ex) {
      Logger.getLogger(TestBDJugador.class.getName()).log(Level.SEVERE, null, ex);
    }
    jugadores=jugadorjpa.findJugadorEntities(); //Actualizacion de lista
    //Comprobacion de numero de jugadores despues de eliminacion
    Assert.assertEquals(2, jugadores.size());
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
      System.out.println("Jugador FechaNacimiento:"+jugadores.get(i).getFechaNacimiento()+"\n");
  }
  }
}
