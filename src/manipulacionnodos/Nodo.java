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
public class Nodo {
    private int identity=0;
    private String name="";

    public String getName() {
        return name;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  identity+"-"+name;
    }
}
