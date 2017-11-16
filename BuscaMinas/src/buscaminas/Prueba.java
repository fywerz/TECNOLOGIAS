/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import logica.Contador;

/**
 *
 * @author EricK
 */
public class Prueba {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Thread contador=new Thread(new Contador(30));
    contador.start();
    System.out.println("Segundos");
  }
  
}
