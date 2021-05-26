/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package manipulacionnodos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import org.neo4j.driver.types.Node;

import static org.neo4j.driver.Values.parameters;
/**
 *
 * @author Rocío
 */
public class RelacionNodoControler {
   
    Conexiones cn = new Conexiones();
    ArrayList<RelacionNodo> arrayList;

    public RelacionNodoControler(){}

    public RelacionNodoControler(ArrayList<RelacionNodo> arrayList){
        this.arrayList = arrayList;
    }

    public void crearRelacionNodo(RelacionNodo relacionNodo) {
        try (Session session = cn.conectar()) {
            if (validaNodo(relacionNodo)) {
                Result result = session.run("match (nI:Lugar{nombre:$nI}) match(nF:Lugar {nombre:$nF} ) create ruta = (nI)-[:Ruta {costo: $costo}]->(nF) return ruta",
                        parameters("nI", relacionNodo.getnInicio(),"costo",relacionNodo.getCosto(),"nF",relacionNodo.getnFinal()));
                        if (result.list().size() > 0) {
                            Record r = result.list().get(0);
                            arrayList.add(relacionNodo);
                            System.out.println("Ruta creada correctamente");
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

    public ArrayList<RelacionNodo> consultarRelacionNodo() {
        ArrayList<RelacionNodo> arrayList = new ArrayList<>();
        try(Session session = cn.conectar()){
           // Result result = session.run("match (nI:Lugar)-[r:Ruta]->(nF:Lugar) return nI.nombre as Inicio,nF.nombre as Final,r.costo as Costo");
           Result result = session.run("match (nI:Loc)-[r:ROAD]->(nF:Loc) return nI.name as Inicio,nF.name as Final,r.cost as Costo");
            for (Record record: result.list()) {
                RelacionNodo rN = new RelacionNodo();
                rN.setnInicio(record.get("Inicio").asString());
                rN.setnFinal(record.get("Final").asString());
                if( !record.get("Costo").isNull()){
                    rN.setCosto(record.get("Costo").asInt());
                }
                arrayList.add(rN);
            }
        }catch(Exception e){
            System.out.println("Error Conexion Neo4j: " + e.getMessage());
        }finally{
            cn.desconectar();
        }
        return arrayList;
    }


  
    public boolean validaNodo(RelacionNodo relacionNodo) {

        for (RelacionNodo rNodo : arrayList) {
            if (rNodo.getnInicio().equals(relacionNodo.getnInicio()) && rNodo.getnFinal().equals(relacionNodo.getnFinal())) {
                System.out.println("La relacion de "+ relacionNodo.getnInicio() +" con " + relacionNodo.getnFinal()+" ya existe, verifique");
                return false;
            }
        }
        return true;
    }
    
}
