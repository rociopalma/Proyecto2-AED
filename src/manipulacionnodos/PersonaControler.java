/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacionnodos;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import static org.neo4j.driver.Values.parameters;

/**
 *
 * @author Rocío
 */
public class PersonaControler {
    
    Conexiones cn = new Conexiones();
    
    public void crearNodo(Persona clasePersona) {
        
        try (Session session = cn.conectar()) {
           // if (validaNodo(nameNodo)) {

                Result result = session.run("CREATE (nodo: Persona {nombre: $name, edad: $edad, correo: $correo}) RETURN nodo",
                        parameters("name", clasePersona.getNombre(), "edad", clasePersona.getEdad(),
                                "correo", clasePersona.getCorreo()));
                if (result.list().size() > 0) {
                    System.out.println("Nodo creado correctamente");
                } else {
                    System.out.println("No se pudo crear el nodo, favor de verificar.");
                }
            //}

        } catch (Exception e) {
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
    }
    
}
