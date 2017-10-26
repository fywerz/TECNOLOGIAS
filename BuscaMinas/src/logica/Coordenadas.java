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
public class Coordenadas {
  private int coordenadaX; //atributo de coordenada X
  private int coordenadaY; //atributo de coordenada Y

  
  public Coordenadas(int coordenadaX, int coordenadaY) {
    this.coordenadaX = coordenadaX;
    this.coordenadaY = coordenadaY;
  }
  
  
  
  //Setters y getters

  public int getCoordenadaX() {
    return coordenadaX;
  }

  public void setCoordenadaX(int coordenadaX) {
    this.coordenadaX = coordenadaX;
  }

  public int getCoordenadaY() {
    return coordenadaY;
  }

  public void setCoordenadaY(int coordenadaY) {
    this.coordenadaY = coordenadaY;
  }
  
}
