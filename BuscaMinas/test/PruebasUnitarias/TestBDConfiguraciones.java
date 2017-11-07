/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasUnitarias;

import JPA.controller.ConfiguracionesJpaController;
import JPA.controller.exceptions.NonexistentEntityException;
import JPA.entidades.Configuraciones;
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
public class TestBDConfiguraciones {
  ConfiguracionesJpaController configuracionjpa=new ConfiguracionesJpaController();
  List<Configuraciones> configuraciones=configuracionjpa.findConfiguracionesEntities();
  public TestBDConfiguraciones() {
  }
  
  @Before
  public void before(){
    System.out.println("Before");
    configuraciones=configuracionjpa.findConfiguracionesEntities();
  }
  
  @Test
  public void testBDLeer(){
    System.out.println("Leer");
    imprimirConfiguraciones();
    Assert.assertEquals(1, configuraciones.get(0).getDificultad());
    Assert.assertEquals(10, configuraciones.get(0).getVolumen());    
  }
  
  @Test
  public void testBDEditar(){
    System.out.println("Editar");
    imprimirConfiguraciones();
    Configuraciones editC=new Configuraciones(1, 2, 20);
    try {
      configuracionjpa.edit(editC);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(TestBDConfiguraciones.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(TestBDConfiguraciones.class.getName()).log(Level.SEVERE, null, ex);
    }
    configuraciones=configuracionjpa.findConfiguracionesEntities();
    imprimirConfiguraciones();
    Assert.assertEquals(2, configuraciones.get(0).getDificultad());
    Assert.assertEquals(20, configuraciones.get(0).getVolumen());
    
    Configuraciones returnC=new Configuraciones(1, 1, 10);
    try {
      configuracionjpa.edit(editC);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(TestBDConfiguraciones.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(TestBDConfiguraciones.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
  public void imprimirConfiguraciones(){
    for (int i = 0; i < configuraciones.size(); i++) {
      System.out.println("Configuraciones ID: "+configuraciones.get(i).getJugadoridJugador());
      System.out.println("Configuraciones Dificultad: "+configuraciones.get(i).getDificultad());
      System.out.println("Configuraciones Volumen: "+configuraciones.get(i).getVolumen());      
    }
  }
  
}
