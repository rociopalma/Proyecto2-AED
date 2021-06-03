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
public class nodoPersona {
    private Persona clasePersona;
    private nodoPersona izquierda, derecha;

    public nodoPersona(Persona clasePersona) {
        this.clasePersona = clasePersona;
        this.izquierda = this.derecha = null;
    }

    public Persona getClasePersona() {
        return clasePersona;
    }


    

    public nodoPersona getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(nodoPersona izquierda) {
        this.izquierda = izquierda;
    }

    public nodoPersona getDerecha() {
        return derecha;
    }

    public void setDerecha(nodoPersona derecha) {
        this.derecha = derecha;
    }

    public void imprimirDato() {
        System.out.println(this.clasePersona.toString());
    }
}
