/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author fywer
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICuenta extends Remote {
    public Cuenta autenticar (Cuenta cuenta) throws RemoteException;
    public void registrar(Cuenta cuenta) throws RemoteException;        
    public void desconectar (Cuenta cuenta) throws RemoteException;
    public void recuperar(Cuenta cuenta) throws RemoteException;
}
//registrame, regístrate 