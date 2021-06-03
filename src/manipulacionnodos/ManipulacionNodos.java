/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacionnodos;

import java.util.ArrayList;
import java.util.Scanner;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import static org.neo4j.driver.Values.parameters;

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
        (new ManipulacionNodos()).Menu();
    }

    public void Menu() {
        int opcion = 1;
        while (opcion != 5) {
            System.out.println("~~~~~~~~~~~~Bienvenido~~~~~~~~~~~~");
            System.out.println("Por favor elige una de las siguientes opciones");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Conoce los productos que tenemos para ti!");
            System.out.println("4. Eliminar cuenta");
            System.out.println("5. Imprimir usuarios");
            System.out.println("6. Imprimir usuarios con árbol");
            System.out.println("7. ---SALIR---");
            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    crearPersona();

                    break;
                case 2:
                    encontrarID();

                    break;

                case 3:
                    mostrarProductos();

                    break;

                case 4:
                    eliminarPersona();
                    break;
                    
                    case 5:
                    mostrarPersonas();
                    break;
                    
                    case 6:
                    arbolMostrar();
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

    public void crearPersona() {
        leer.useDelimiter("\n");
        Persona clasePersona = new Persona();
        PersonaControler personaControler = new PersonaControler();
        System.out.println("Ingresa tu nombre: ");
        //clasePersona.setNombre("Sofia Catellanos");
        clasePersona.setNombre(leer.next());
        System.out.println("Ingresa tu edad: ");
        //clasePersona.setEdad(19);
        clasePersona.setEdad(leer.nextInt());
        System.out.println("Ingresa tu correo electrónico: ");
        //clasePersona.setCorreo("sofiaG@gmail.com");
        clasePersona.setCorreo(leer.next());
        personaControler.crearNodo(clasePersona);
        if (clasePersona.getID() > 0) {
            relacionTiene(clasePersona);
        }
    }

    public void relacionTiene(Persona persona) {
        Tiene claseTiene = new Tiene();
        RelacionTiene controlerTiene = new RelacionTiene();

        System.out.println("¿Qué tipo de piel tiene " + persona.getNombre() + "?");
        System.out.println("1. Piel Mixta: En la piel mixta la zona “T” (frente, barbilla y nariz) es grasa, se notan los poros agrandados y con algunas "
                + "\nimpurezas o rastros de acné mientras que las mejillas presentan una piel normal o con tendencia a seca.");
        System.out.println("2. Piel Seca: Este tipo de piel produce menos sebo que la piel normal y como consecuencia carece de los lípidos que retienen la "
                + "\nhumedad y que forman una capa protectora. En la piel seca existe un deterioro de esta función de la barrera. ");
        System.out.println("3. Piel Grasa: se caracteriza por una producción acrecentada de sebo. A ésta hiperproducción se le conoce como seborrea.  "
                + "\nEste tipo de piel es propensa al acné, en ocasiones leve, pero también puede ser acné severo no solo en la cara sino en "
                + "\ncuello, hombros espalda y pecho.");
        System.out.println("4. Piel Normal: Es una piel que se encuentra equilibrada al no ser ni "
                + "\ndemasiado grasa ni demasiado seca. Tiene poros finos, buena circulación sanguínea, "
                + "\ntextura aterciopelada suave y lisa, ausencia de impurezas, y no es sensible ni se irrita "
                + "\nfácilmente.");
        System.out.println("5. Salir sin asignar");
        int opc = leer.nextInt();

        switch (opc) {
            case 1:
                claseTiene.setTipoPiel("Piel Mixta");
                controlerTiene.crearRelacion(persona, claseTiene);
                Menu();
                break;
            case 2:
                claseTiene.setTipoPiel("Piel Seca");
                controlerTiene.crearRelacion(persona, claseTiene);
                Menu();
                break;
            case 3:
                claseTiene.setTipoPiel("Piel Grasa");
                controlerTiene.crearRelacion(persona, claseTiene);
                Menu();
                break;
            case 4:
                claseTiene.setTipoPiel("Piel Normal");
                controlerTiene.crearRelacion(persona, claseTiene);
                Menu();
                break;

            default:
                Menu();
                break;
        }//fin de switch

    }//fin relacion tiene

    public void mostrarPersonas() {
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        PersonaControler personaControler = new PersonaControler();
        listaPersonas = personaControler.consulta();
        for (Persona persona : listaPersonas) {
            System.out.println(persona.toString());
        }
    }// fin mostrar personas

    public void encontrarID() {
        Persona persona = new Persona();
        int IDbusca = 0;
        PersonaControler personaControler = new PersonaControler();
        System.out.println("Ingrese su Id");
        IDbusca = leer.nextInt();
        persona = personaControler.consultarID(IDbusca);

        if (IDbusca == persona.getID()) {
            System.out.println(persona.toString());
            
            imprimirProductoE(persona.getID());
            
        } else {
            System.out.println("Persona no encontrada, por favor registrarse");
        }
    }

    public void mostrarProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ProductoControler productoControler = new ProductoControler();
        listaProductos = productoControler.consulta();
        for (Producto producto : listaProductos) {
            System.out.println(producto.toString());
        }
    }//fin de mostrar productos

    public void eliminarPersona() {
        leer.useDelimiter("\n");
        int IDbusca = 0;
        Persona persona = new Persona();
        PersonaControler personaControler = new PersonaControler();
        System.out.println("Por favor ingrese el ID de la persona que desea eliminar");
        IDbusca = leer.nextInt();
        persona = personaControler.consultarID(IDbusca);

        if (IDbusca == persona.getID()) {
            System.out.println(persona.toString());
            System.out.println("Está seguro de eliminar a la persona " + persona.getNombre() + " SI(1)/NO(0)?");
            if (leer.nextInt() == 1) {
                personaControler.EliminarNodo(persona);
                System.out.println("Persona eliminada correctamente");
            } else {
                System.out.println("No se pudo eliminar a la persona, favor verificar");
            }
        } else {
            System.out.println("Persona no encontrada");
        }

    }
    
    public void arbolMostrar(){
        arbolPersona arbol = new arbolPersona();
        PersonaControler personaControler = new PersonaControler();
         arbol = personaControler.consultaArbol();
        System.out.println("PREORDEN:");
        arbol.preorden();
        System.out.println("POSTORDEN:");
        arbol.postorden();
        System.out.println("INORDEN:");
        arbol.inorden();
    }
    
    public void imprimirProductoE(int ID){
       ArrayList<Producto> listaP = new ArrayList<>();
       Tiene claseTiene = new Tiene();
       Producto claseProducto = new Producto();
        ProductoControler productoControler = new ProductoControler();
        String tipo = productoControler.mostrarTipoPiel(ID);
        //listaP = productoControler.consultaProductoTipo(tipo);
        listaP = productoControler.consultaProductoTipo("Piel Mixta");
        for (Producto producto : listaP) {
            System.out.println(producto.toString());
        } 
    }//fin de imprimir productos
    
    
    
    

}//fin de clase
