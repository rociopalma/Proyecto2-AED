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
            System.out.println("5. ---SALIR---");
            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    crearPersona();

                    break;
                case 2:
                    encontrarID();

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
        relacionTiene(clasePersona);
        
    }
    
    public void relacionTiene(Persona persona){
        Tiene claseTiene = new Tiene();
        RelacionTiene controlerTiene = new RelacionTiene();
        
        
        System.out.println("¿Qué tipo de piel tiene "+persona.getNombre()+"?");
        System.out.println("1. Piel Mixta");
        System.out.println("2. Piel Seca");
        System.out.println("3. Piel Grasa");
        System.out.println("4. Piel Normal");
        System.out.println("5. Salir sin asignar");
        int opc = leer.nextInt();
        
        switch (opc){
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
    
    /*public String initSesion(){
        try (Session session = cn.conectar()) {
            if (validaNodo(nameNodo)) {
                System.out.println("Ingresa tu nombre: ");
                String nombre = leer.next();
                //clasePersona.setNombre("Sofia Catellanos");
                return nombre;
                } else {
                    System.out.println("No existe "+nombre+ " en el sistema, por favor registrarse");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        
        
    }//fin init sesion
    
    public boolean validaNodo(String nameNodo) {

        for (Nodo nodo : arrayList) {
            if (nodo.getName().equals(nameNodo)) {
                System.out.println("El nodo : " + nameNodo + " que esta ingresando ya existe, favor de verificar");
                return false;
            }
        }
        return true;
    }// fin de validar nodo*/
    public void mostrarPersonas(){
        ArrayList <Persona> listaPersonas = new ArrayList<>();
        PersonaControler personaControler = new PersonaControler();
        listaPersonas = personaControler.consulta();
        for(Persona persona:listaPersonas){
            System.out.println(persona.toString());
        }
    }// fin mostrar personas
    
    public void encontrarID(){
        Persona persona = new Persona();
        int IDbusca = 0;
        PersonaControler personaControler = new PersonaControler();
        System.out.println("Ingrese su Id");
        IDbusca = leer.nextInt();
        persona = personaControler.consultarID(IDbusca);
        
        if(IDbusca == persona.getID()){
         System.out.println(persona.toString());   
        }
        else{
            System.out.println("Persona no encontrada, por favor registrarse");
        }
    }
    
}//fin de clase
