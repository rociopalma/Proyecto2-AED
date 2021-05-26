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
public class RelacionNodo {
    private String nInicio ="";
    private String nFinal = "";
    private int costo=0;

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public void setnFinal(String nFinal) {
        this.nFinal = nFinal;
    }

    public void setnInicio(String nInicio) {
        this.nInicio = nInicio;
    }

    public int getCosto() {
        return costo;
    }
    public String getnFinal() {
        return nFinal;
    }
    public String getnInicio() {
        return nInicio;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Inicio: "+ nInicio +" Final: "+nFinal+" Costo: "+costo;
    }
}
