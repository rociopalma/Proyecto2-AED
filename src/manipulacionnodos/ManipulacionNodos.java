/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacionnodos;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rocío
 */
public class ManipulacionNodos {
    Scanner leer = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //(new ManipulacionNodos()).Menu();
        (new ManipulacionNodos()).relacionTiene();
    }

    public void Menu() {
        int opcion = 1;
        while (opcion != 5) {
            System.out.println("~~~~~~~~~~~~Bienvenido~~~~~~~~~~~~");
            System.out.println("Por favor elija una de las siguientes opciones");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Conoce los productos que tenemos para ti!");
            System.out.println("4. Eliminar cuenta");
            System.out.println("5. ---SALIR---");
            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    Inicializar();

                    break;
                case 2:
                    ;

                    break;

                case 3:
                    ;

                    break;

                default:
                    System.out.println("°°°°°°°°°°°°°°Vuelva pronto°°°°°°°°°°°°°°");
                    break;
            }//fin case

        }//fin while
        

    }

    public void Inicializar() {
        
        ArrayList<Nodo> arregloNodos = new ArrayList<>();
        buscarNodos();
        NodoControler controlarNodo = new NodoControler(arregloNodos);
        controlarNodo.crearNodo("C");
        buscarNodos();
    }

    public void buscarNodos() {
        ArrayList<Nodo> arregloNodos = new ArrayList<>();
        NodoControler controlarNodo = new NodoControler(arregloNodos);
        arregloNodos = controlarNodo.consultarNodo();

        for (Nodo rNodo : arregloNodos) {
            System.out.println(rNodo.toString());
        }

    }
    
    public void crearPersona(){
        Persona clasePersona = new Persona();
        PersonaControler personaControler = new PersonaControler();
        clasePersona.setNombre("Sofia Catellanos");
        clasePersona.setEdad(19);
        clasePersona.setCorreo("sofiaG@gmail.com");
        personaControler.crearNodo(clasePersona);
        
    }
    
    public void relacionTiene(){
        Persona clasePersona = new Persona();
        Tiene claseTiene = new Tiene();
        RelacionTiene controlerTiene = new RelacionTiene();
        clasePersona.setNombre("Sofia Catellanos");
        claseTiene.setTipoPiel("Piel Mixta");
        controlerTiene.crearRelacion(clasePersona, claseTiene);
    }
}//fin de clase
