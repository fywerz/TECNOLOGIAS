/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasUnitarias;

import logica.Coordenadas;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author EricK
 */
public class TestCoordenadas {
  
  public TestCoordenadas() {
  }
  Coordenadas coor=new Coordenadas(2, 3);
  
  @Test
  public void testEquals(){
    boolean igual=coor.equals(new Coordenadas(2,3));
    Assert.assertEquals(true , igual);
  }
}
