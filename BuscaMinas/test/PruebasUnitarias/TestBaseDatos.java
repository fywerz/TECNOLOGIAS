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
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author EricK
 */
public class TestBaseDatos {
  
  public TestBaseDatos() {
  }
  
  @Before
  public void before(){
    
  }
  @After
  public void after(){
    
  }
  
  @Test
  public void testJugador(){
    JugadorJpaController jugadorjpa=new JugadorJpaController();
    List <Jugador> jugadores=jugadorjpa.findJugadorEntities();
    Assert.assertEquals(new Integer(2), jugadores.get(1).getIdJugador());
    Assert.assertEquals("EricK", jugadores.get(1).getNombreJugador());
    Assert.assertEquals(false, jugadores.get(1).getGenero());
    Assert.assertEquals(Date.valueOf("1997-08-01"), jugadores.get(1).getFechaNacimiento());
    Assert.assertEquals(0, jugadores.get(1).getPuntaje(), 0);
    Jugador nuevoJ=new Jugador();
    nuevoJ.setNombreJugador("NuevoJ");
    nuevoJ.setFechaNacimiento(Date.valueOf("1997-08-01"));
    nuevoJ.setGenero(true);
    nuevoJ.setPuntaje(0);
    nuevoJ.setContrasena("NuevaContraseña");
    //jugadorjpa.create(nuevoJ);
    jugadores=jugadorjpa.findJugadorEntities();
  
   
    
    
  }
}
