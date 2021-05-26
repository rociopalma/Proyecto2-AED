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
import org.neo4j.driver.types.Node;
import org.neo4j.driver.Record;

/**
 *
 * @author Rocío
 */
public class NodoControler {
    Conexiones cn = new Conexiones();
    ArrayList<Nodo> arrayList;

    public NodoControler() {

    }

    public NodoControler(ArrayList<Nodo> arrayList) {
        this.arrayList = arrayList;
    }

    public void crearNodo(String nameNodo) {
        try (Session session = cn.conectar()) {
            if (validaNodo(nameNodo)) {

                Result result = session.run("CREATE (nodo: Lugar {nombre: $name}) RETURN nodo",
                        parameters("name", nameNodo));
                if (result.list().size() > 0) {
                   /*
                    Record r = result.list().get(0);
                    Node lugarNode = r.get("nodo").asNode();

                    Nodo n = new Nodo();
                    n.setName(lugarNode.get("nombre").asString());
                    n.setIdentity(lugarNode.get("identity").asInt());
                    arrayList.add(n);
                    */
                    System.out.println("Nodo creado correctamente");
                } else {
                    System.out.println("No se pudo crear el nodo, favor de verificar.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
    }

    public ArrayList<Nodo> consultarNodo() {
        ArrayList<Nodo> arrayList = new ArrayList<>();
        try(Session session = cn.conectar()){
            Result result = session.run("MATCH (nodo:Lugar) RETURN nodo ");
            for (Record record: result.list()) {
                Node lugarNode = record.get("nodo").asNode();
                Nodo n = new Nodo();
                //n.setIdentity(lugarNode.get("identity").asInt());
                n.setName(lugarNode.get("nombre").asString());
                arrayList.add(n);
            }
        }catch(Exception e){
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        }finally{
            cn.desconectar();
        }
        return arrayList;
    }


    public boolean validaNodo(String nameNodo) {

        for (Nodo nodo : arrayList) {
            if (nodo.getName().equals(nameNodo)) {
                System.out.println("El nodo : " + nameNodo + " que esta ingresando ya existe, favor de verificar");
                return false;
            }
        }
        return true;
    }

}
