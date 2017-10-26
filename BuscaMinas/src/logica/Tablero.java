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
public final class Tablero {
  private int[][] arrCasilla; //Arreglo de casillas con numero de minas (0 sin minas alrededor, 1 una mina alrededor, 9 mina, etc)
  private int numMinas; //numero de minas en el campo
  private int tamano; //tamaño del cuadrante del tablero
  
  public Tablero(int numMinas, int tamano) {
    this.numMinas = numMinas;
    this.tamano=tamano;
    inicializar();
    sortear();
  }

  public void sortear() {
    for (int i = 0; i < numMinas; i++) {
      int xCor = (int) (Math.random() * (tamano -1)); //xCor variable auxiliar de coordenada X
      int yCor = (int) (Math.random() * (tamano -1)); //yCor variable auxiliar de coordenada Y
      if(arrCasilla[xCor][yCor]!=9){
        arrCasilla[xCor][yCor]=9;
      }else{
        i--;
      }
      
    }
  }
  
  public void inicializar(){
    for (int i = 0; i < tamano; i++) {
      for (int j = 0; j < tamano; j++) {
        arrCasilla[i][j]=0;
      }
      
    }
  }
  
  //Setters y Getters

  public int[][] getArrCasilla() {
    return arrCasilla;
  }

  public void setArrCasilla(int[][] arrCasilla) {
    this.arrCasilla = arrCasilla;
  }

  public int getNumMinas() {
    return numMinas;
  }

  public void setNumMinas(int numMinas) {
    this.numMinas = numMinas;
  }

  public int getTamano() {
    return tamano;
  }

  public void setTamano(int tamano) {
    this.tamano = tamano;
  }
  
  
}
