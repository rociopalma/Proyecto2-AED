/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacionnodos;

/**
 *
 * @author Rocío
 */
public class Producto {
    
    private String nombre;
    private int ID;
    private int valor;
    
    public Producto(){
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    
    
    public String toString() {
        return nombre+", ID: "+ID+", costo: "+valor;
    }
}
