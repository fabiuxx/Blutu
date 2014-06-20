/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bluetooth.server;

/**
 *
 * @author Acer
 */
public class Main {
    public static void main(String[] args) {
        try {
            SimpleBluetoothServer server = new SimpleBluetoothServer();
            server.Start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
