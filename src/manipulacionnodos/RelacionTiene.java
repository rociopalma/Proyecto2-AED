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
public class RelacionTiene {
    
    Conexiones cn = new Conexiones();
    
    
    public void crearRelacion(Persona clasePersona, Tiene claseTiene) {
        
        try (Session session = cn.conectar()) {
           // if (validaNodo(nameNodo)) {

                 session.run("MATCH (p:Persona{nombre: $nombre}) MATCH (t:TipoPiel{nombre: $nombreT}) CREATE (p)-[:TIENE]->(t)",
                        parameters("nombre", clasePersona.getNombre(), "nombreT", claseTiene.getTipoPiel()));
               
                    System.out.println("Relación creada correctamente");
                
            //}

        } catch (Exception e) {
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
    }//fin de metodo
}
