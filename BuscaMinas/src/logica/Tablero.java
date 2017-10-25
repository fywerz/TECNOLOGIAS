/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author EricK
 */
public class Tablero {
  private int[][] arrCasilla; //Arreglo de casillas con numero de minas (0 sin minas alrededor, 1 una mina alrededor, 9 mina, etc)
  private int numMinas; //numero de minas en el campo
  private int tamano; //tamaño del cuadrante del tablero
  
  public Tablero(int numMinas, int tamano) {
    this.numMinas = numMinas;
    sortear();
  }

  private void sortear() {
    for (int i = 0; i < tamano; i++) {
      int[] is = arrCasilla[i];
      
    }
  }
  
  
  
}
