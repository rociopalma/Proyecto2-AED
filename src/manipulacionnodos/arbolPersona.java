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
public class arbolPersona {
    private nodoPersona raiz;

    public arbolPersona() {

    }

    public boolean existe(String busqueda) {
        return existe(this.raiz, busqueda);
    }

    private boolean existe(nodoPersona n, String busqueda) {
        if (n == null) {
            return false;
        }
        if (n.getClasePersona().getNombre().equals(busqueda)) {
            return true;
        } else if (busqueda.compareTo(n.getClasePersona().getNombre()) < 0) {
            return existe(n.getIzquierda(), busqueda);
        } else {
            return existe(n.getDerecha(), busqueda);
        }

    }

    public void insertar(Persona clasePersona) {
        if (this.raiz == null) {
            this.raiz = new nodoPersona(clasePersona);
        } else {
            this.insertar(this.raiz, clasePersona);
        }
    }

    private void insertar(nodoPersona padre, Persona clasePersona) {
        if (clasePersona.getID() > 0) {
            if (padre.getDerecha() == null) {
                padre.setDerecha(new nodoPersona(clasePersona));
            } else {
                this.insertar(padre.getDerecha(), clasePersona);
            }
        } else {
            if (padre.getIzquierda() == null) {
                padre.setIzquierda(new nodoPersona(clasePersona));
            } else {
                this.insertar(padre.getIzquierda(), clasePersona);
            }
        }
    }

    private void preorden(nodoPersona n) {
        
        if (n != null) {
            n.imprimirDato();
            preorden(n.getIzquierda());
            preorden(n.getDerecha());
        }
    }

    private void inorden(nodoPersona n) {
        if (n != null) {
            inorden(n.getIzquierda());
            n.imprimirDato();
            inorden(n.getDerecha());
        }
    }

    private void postorden(nodoPersona n) {
        if (n != null) {
            postorden(n.getIzquierda());
            postorden(n.getDerecha());
            n.imprimirDato();
        }
    }

    public void preorden() {
        this.preorden(this.raiz);
    }

    public void inorden() {
        this.inorden(this.raiz);
    }
    
    public void postorden(){
        this.postorden(this.raiz);
    }
}
