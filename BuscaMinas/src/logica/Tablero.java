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
  private int arrCasilla[][]; //Arreglo de casillas con numero de minas (0 sin minas alrededor, 1 una mina alrededor, 9 mina, etc)
  private int numMinas; //numero de minas en el campo
  private int tamano; //tamaño del cuadrante del tablero
  
  public Tablero(int numMinas, int tamano) {
    this.numMinas = numMinas;
    this.tamano=tamano;
    this.arrCasilla=new int [tamano][tamano];
    inicializar();
    sortear();
    contarAlrededor();
  }

  /**
   * Metodo para sortear las minas
   */
  public void sortear() {
    for (int i = 0; i < numMinas; i++) {
      int xCor = (int) (Math.random() * (tamano -1)); //xCor variable auxiliar de coordenada X
      int yCor = (int) (Math.random() * (tamano -1)); //yCor variable auxiliar de coordenada Y
    //Condicion que verifica que no se repita una mina en el mismo lugar
      if(arrCasilla[xCor][yCor]!=9){
        arrCasilla[xCor][yCor]=9;
      }else{
        i--;
      }
      
    }
  }
  
  /**
   * Metodo para inicializar el arreglo en 0
   */
  public void inicializar(){
    for (int i = 0; i < tamano; i++) {
      for (int j = 0; j < tamano; j++) {
        arrCasilla[i][j]=0;
      }
      
    }
  }
  
  /**
   * Metodo para poner en el arreglo los numeros de cuantas minas tiene la casilla alrededor
   */
  public void contarAlrededor(){
  //Ciclo para repasar todo el arreglo
    for (int i = 0; i < arrCasilla.length; i++) {
      for (int j = 0; j < arrCasilla.length; j++) {
        System.out.println("____________________________________________________________________________________-");
        System.out.println("probando Casilla ["+i+"]["+j+"]");
        //Ciclo que repasa las casillas alrededor de la casilla actual
        int contadorMinas=0;
        System.out.println("si"+arrCasilla[i][j]+"diferente 9");
        if (arrCasilla[i][j]!=9){
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < 3; l++) {
            System.out.println(i+"+("+k+"-1)");
            int corX=i+(k-1);
            int corY=j+(l-1);
            //Condicion que verifica que no se repase a si misma la casilla, y las oordenadas no se salgan del arreglo
            System.out.println("probando Casilla ["+i+"]["+j+"] contra"+"Casilla ["+corX+"]["+corY+"]");
            if((i!=corX && j!=corY) && !(corX<=0 || corX>arrCasilla.length-1) && !(corY<=0 || corY>arrCasilla.length-1)){
              //COndicion que verifica si hay mina
              System.out.println("Checando casilla["+corX+"]["+corY+"] es igual 9");
              if(arrCasilla[corX][corY]==9){
                contadorMinas++;
              }
            }        
          }
         }
          arrCasilla[i][j]=contadorMinas; //Al final del conteo se le asigna el numero
        }
      
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
