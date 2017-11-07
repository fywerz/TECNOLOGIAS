/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasUnitarias;

import JPA.controller.PartidasganadasJpaController;
import JPA.controller.exceptions.NonexistentEntityException;
import JPA.controller.exceptions.PreexistingEntityException;
import JPA.entidades.Configuraciones;
import JPA.entidades.Partidasganadas;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author EricK
 */
public class TestDBPartidasGanadas {
  PartidasganadasJpaController partidajpa=new PartidasganadasJpaController();
  List<Partidasganadas> partidas=partidajpa.findPartidasganadasEntities();
  
  public TestDBPartidasGanadas() {
  }
  
  @Before
  public void before(){
    System.out.println("Before");
    partidas=partidajpa.findPartidasganadasEntities();
  }
  
  @Test
  public void testBDLeer(){
    System.out.println("Leer");
    imprimirPartidas();
    Assert.assertEquals(5, partidas.get(0).getFacil(), 0);
    Assert.assertEquals(3, partidas.get(0).getIntermedio(), 0);
    Assert.assertEquals(2, partidas.get(0).getDificil(), 0);
  }
  
  @Test
  public void testBDEditar(){
    System.out.println("Editar");
    imprimirPartidas();
    Partidasganadas editP=new Partidasganadas(1, 4, 2, 1);
    try {
      partidajpa.edit(editP);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(TestDBPartidasGanadas.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(TestDBPartidasGanadas.class.getName()).log(Level.SEVERE, null, ex);
    }
    partidas=partidajpa.findPartidasganadasEntities();
    imprimirPartidas();
    
    Assert.assertEquals(4, partidas.get(0).getFacil(), 0);
    Assert.assertEquals(2, partidas.get(0).getIntermedio(), 0);
    Assert.assertEquals(1, partidas.get(0).getDificil(), 0);
    
    Partidasganadas returnP=new Partidasganadas(1, 5, 3, 2);
    try {
      partidajpa.edit(returnP);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(TestDBPartidasGanadas.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(TestDBPartidasGanadas.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @Test
  public void testDBCrearEliminar(){
    System.out.println("Crear");
    imprimirPartidas();
    Partidasganadas crearP=new Partidasganadas(3, 6, 5, 1);
    try {
      partidajpa.create(crearP);
    } catch (PreexistingEntityException ex) {
      Logger.getLogger(TestBDConfiguraciones.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(TestBDConfiguraciones.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    System.out.println("Creado");
    partidas=partidajpa.findPartidasganadasEntities();
    Assert.assertEquals(6, partidas.get(partidas.size()-1).getFacil(), 0);
    Assert.assertEquals(5, partidas.get(partidas.size()-1).getIntermedio(), 0);
    Assert.assertEquals(1, partidas.get(partidas.size()-1).getDificil(), 0);
    Assert.assertEquals(2, partidas.size());
    imprimirPartidas();
    System.out.println("Eliminar");
    try {
      partidajpa.destroy(partidas.get(partidas.size()-1).getJugadoridJugador());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(TestBDConfiguraciones.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("Eliminado");
    imprimirPartidas();
    partidas=partidajpa.findPartidasganadasEntities();
    Assert.assertEquals(1, partidas.size());
  }
  
  public void imprimirPartidas(){
    for (int i = 0; i < partidas.size(); i++) {
       System.out.println("Partidas ID: "+partidas.get(i).getJugadoridJugador());
       System.out.println("Partidas Facil: "+partidas.get(i).getFacil());
       System.out.println("Partidas Intermedio: "+partidas.get(i).getIntermedio());
       System.out.println("Partidas Dificil: "+partidas.get(i).getDificil()+"\n");
    }
  }
}
