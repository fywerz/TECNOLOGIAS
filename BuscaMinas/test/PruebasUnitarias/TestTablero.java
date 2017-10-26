package PruebasUnitarias;

import logica.Tablero;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author EricK
 */
public class TestTablero {
  Tablero tab;
  int [][] arrPrueba;
  public TestTablero() {
  }
    
  @Before
  public void before() {
    System.out.println("Before");
    tab=new Tablero();
  }
  
  @After
  public void after() {
    System.out.println("After");
  }
  @Test
  public void TestInicializar(){
    tab.setTamano(8);
    tab.setArrCasilla(new int [10][10]);
    arrPrueba=tab.getArrCasilla();
    System.out.println("Test Inicializar");
  int contadorNum=0; //Contador para ver si hay otros numeros ademas de 0 
  tab.inicializar(); //Uso de metodo Inicializar
    for (int i = 0; i < arrPrueba.length; i++) {
      for (int j = 0; j < arrPrueba.length; j++) {
        if(arrPrueba[i][j]!=0){
          contadorNum++;
        }
       }      
    }
    tab.imprimirMatriz();
    Assert.assertEquals(0, contadorNum);
  }
  
  
  @Test
  public void testSortear(){
    System.out.println("Test Sortear");
    tab.setNumMinas(10);
    tab.setTamano(8);
    tab.setArrCasilla(new int [10][10]);
    tab.inicializar();
    tab.sortear();
    arrPrueba=tab.getArrCasilla();
    int contadorMinas=0; //Contador para minas en el arreglo
    /**
     * Ciclo para checar uno por uno cada elemento del arreglo
     */
    for (int i = 0; i < arrPrueba.length; i++) {
      for (int j = 0; j < arrPrueba.length; j++) {
        if(arrPrueba[i][j]==9){
          contadorMinas++;
        }
      }
     }
    tab.imprimirMatriz();
    Assert.assertEquals(10, contadorMinas);
    
  }
  
  @Test
  public void testContarAlrededor(){
    System.out.println("Test ContarAlrededor");
    tab.setArrCasilla(new int [10][10]);
    tab.inicializar();
    arrPrueba=tab.getArrCasilla();
    arrPrueba [3][2]=9;
    arrPrueba [5][4]=9;
    tab.setArrCasilla(arrPrueba);
    tab.contarAlrededor();
    tab.imprimirMatriz();
    
    Assert.assertEquals(2, arrPrueba[4][3]);
    Assert.assertEquals(1, arrPrueba[5][3]);
  }

}
