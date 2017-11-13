/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasUnitarias;

import logica.Contador;
import org.junit.Test;

/**
 *
 * @author EricK
 */
public class TestContador {
  private Thread contador= new Thread( new Contador(30));
  
  public TestContador() {
  }
  
  @Test
  public void testRun(){
    contador.start();
  }
}
