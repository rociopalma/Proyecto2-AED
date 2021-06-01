/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacionnodos;

import java.util.ArrayList;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import static org.neo4j.driver.Values.parameters;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;

/**
 *
 * @author Rocío
 */
public class PersonaControler {

    Conexiones cn = new Conexiones();

    public void crearNodo(Persona clasePersona) {

        try ( Session session = cn.conectar()) {
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
    }//fin crear nodo

    public ArrayList<Persona> consulta() {
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        try ( Session session = cn.conectar()) {
            // if (validaNodo(nameNodo)) {

            Result result = session.run("MATCH (n:Persona) RETURN n");

            for (Record record : result.list()) {
                Persona persona = new Persona();
                Node node = record.get("n").asNode();
                persona.setID((int) node.id());
                persona.setNombre(node.get("nombre").asString());
                persona.setEdad(node.get("edad").asInt());
                persona.setCorreo(node.get("correo").asString());
                listaPersonas.add(persona);
            }//fin de for

            //}
        } catch (Exception e) {
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        return listaPersonas;

    }//fin consulta

    public Persona consultarID(int ID) {
        Persona claseP = new Persona();

        try (Session session = cn.conectar()){
            // if (validaNodo(nameNodo)) {
            
            Result result = session.run("MATCH (n:Persona) WHERE ID(n)=$ID RETURN n", parameters("ID", ID));
            //if (!result.list().isEmpty()){
            Record record = result.single();
            Node node = record.get("n").asNode();
            claseP.setID((int) node.id());
            claseP.setNombre(node.get("nombre").asString());
            claseP.setEdad(node.get("edad").asInt());
            claseP.setCorreo(node.get("correo").asString());

            //}
        } catch (Exception e) {
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        return claseP;

    }//fin de consultar ID

}
