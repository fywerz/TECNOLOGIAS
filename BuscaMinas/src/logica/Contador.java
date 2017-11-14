/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EricK
 */
public class Contador implements Runnable{
private int segundos;


  @Override
  public void run() {
    for (int i = segundos; segundos > 0; segundos--) {      
      esperarSegundo();
      System.out.println("Segundos: "+segundos);
    }
   }
//Constructor
  public Contador(int segundos) {
    this.segundos = segundos;
  }
  
  public void esperarSegundo(){
    try {
        Thread.sleep(1000);        
      } catch (InterruptedException ex) {
        System.out.println("Excepcion");
        Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
//Setters y getters
  public int getSegundos() {
    return segundos;
  }

  public void setSegundos(int segundos) {
    this.segundos = segundos;
  }
  
  
  }
