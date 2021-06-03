package manipulacionnodos;
import java.util.ArrayList;
import manipulacionnodos.Persona;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import static org.neo4j.driver.Values.parameters;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.Record;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rocío
 */
public class ProductoControler {
    
     Conexiones cn = new Conexiones();
     
     public ArrayList<Producto> consulta() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        try ( Session session = cn.conectar()) {
            // if (validaNodo(nameNodo)) {

            Result result = session.run("MATCH (n:Producto) RETURN n");

            for (org.neo4j.driver.Record record : result.list()) {
                Producto producto = new Producto();
                Node node = record.get("n").asNode();
                
                producto.setNombre(node.get("nombre").asString());
                producto.setID((int) node.id());
                producto.setValor(node.get("valor").asInt());
                listaProductos.add(producto);
            }//fin de for

            //}
        } catch (Exception e) {
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        return listaProductos;

    }//fin consulta
     
     
     public String mostrarTipoPiel(int ID){
         Tiene claseTiene = new Tiene();
         try ( Session session = cn.conectar()) {
           
                 

         Result result = session.run("MATCH (n:Persona)-[r:TIENE]->(t:TipoPiel) where ID(n) = $ID  return t ", 
                 parameters("ID", ID));
                 if (result.hasNext()){
                
                Record record = result.single();
                Node node = record.get("t").asNode();
                claseTiene.setTipoPiel(node.get("tipoPiel").asString());
                
                 }
         } catch (Exception e) {
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
         return claseTiene.getTipoPiel();
     }//fin de mostrar productos
     
     
     public ArrayList<Producto> consultaProductoTipo(String tipoPiel) {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        try ( Session session = cn.conectar()) {
            // if (validaNodo(nameNodo)) {

            Result result = session.run("MATCH (t:TipoPiel)-[r:NECESITA]->(p:Producto) where t.nombre= $tipoPiel return p", parameters("tipoPiel", tipoPiel));

            for (org.neo4j.driver.Record record : result.list()) {
                Producto producto = new Producto();
                Node node = record.get("p").asNode();
                
                producto.setNombre(node.get("nombre").asString());
                producto.setID((int) node.id());
                producto.setValor(node.get("valor").asInt());
                listaProductos.add(producto);
            }//fin de for

            //}
        } catch (Exception e) {
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        return listaProductos;

    }//fin consulta
     
     
}
