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
    tab=new Tablero(10, 8);
    arrPrueba=tab.getArrCasilla();
  }
  
  @After
  public void after() {
    System.out.println("After");
  }
  @Test
  public void TestInicializar(){
    System.out.println("Test Inicializar");
  int contadorNum=0; //COntador para ver si hay otros numeros ademas de 0 
  tab.inicializar(); //Uso de metodo Inicializar
    for (int i = 0; i < arrPrueba.length; i++) {
      for (int j = 0; j < arrPrueba.length; j++) {
        if(arrPrueba[i][j]!=0){
          contadorNum++;
        }
       }      
    }
    Assert.assertEquals(0, contadorNum);
  }
  
  
  @Test
  public void testSortear(){
    System.out.println("Test Sortear");
    int contadorMinas=0; //Contador para minas en el arreglo
    /**
     * Ciclo para checar uno por uno cada elemento del arreglo
     */
    for (int i = 0; i < arrPrueba.length; i++) {
      for (int j = 0; j < arrPrueba.length; j++) {
        System.out.println("Arreglo ["+i+"]["+j+"]= "+arrPrueba[i][j]);
        if(arrPrueba[i][j]==9){
          contadorMinas++;
        }
      }
     }
    
    Assert.assertEquals(10, contadorMinas);
    
  }
  
  @Test
  public void testContarAlrededor(){
    tab.inicializar();
    arrPrueba [3][2]=9;
    arrPrueba [5][4]=9;
    tab.contarAlrededor();
    Assert.assertEquals(1, arrPrueba[2][1]);
    Assert.assertEquals(2, arrPrueba[5][3]);
  }
  
}
