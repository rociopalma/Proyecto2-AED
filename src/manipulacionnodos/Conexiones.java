/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulacionnodos;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

/**
 *
 * @author Rocío
 */
public class Conexiones {
    
    private Driver driver;

    private static String URL = "bolt://localhost:7687";
    private static String USER = "neo4j";
    private static String PASSWORD = "12345";

    public Session conectar() {
        driver = GraphDatabase.driver(URL, AuthTokens.basic(USER, PASSWORD));
        return driver.session();

    }

    public void desconectar() {
        driver.close();
    }

    
}
