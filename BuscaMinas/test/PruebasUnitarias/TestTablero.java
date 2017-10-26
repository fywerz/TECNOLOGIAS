/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasUnitarias;

import logica.Tablero;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author EricK
 */
public class TestTablero {
  
  public TestTablero() {
  }
    
  @Before
  public void before() {
    Tablero tab= new Tablero(10, 8);
    int arrPrueba [][];
    arrPrueba = tab.getArrCasilla();
  }
  
  @After
  public void after() {
    
  }
  
  @Test
  public void testSortear(){
    
    
  }
  
}
