/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bluetooth.server;

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

/**
 *
 * @author Acer
 */
public class SimpleBluetoothServer {
    public void Start() throws IOException {
        // UUID para el servidor.
        javax.bluetooth.UUID uuid = new javax.bluetooth.UUID("1111", true);
        String connection_string = "btspp://localhost:" + uuid + ";name=SimpleBluetoothServer";
        
        // Habilitamos el servidor.
        StreamConnectionNotifier notifier = (StreamConnectionNotifier)Connector.open(connection_string);
        System.out.println("server is up at " + connection_string);
        System.out.println("waiting for connections ...");
        
        while(true) {
            // Esperamos una conexion.
            StreamConnection connection = notifier.acceptAndOpen();
            // Creamos un hilo.
            new Thread(new ServerThread(connection)).start();
        }
        /*
        // Terminamos la conexion.
        System.out.println("server is down ...");
        notifier.close();
        */
    }
}
