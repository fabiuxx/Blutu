/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bluetooth.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author Acer
 */
public class ServerThread implements Runnable {
    private StreamConnection connection;
    
    public ServerThread(StreamConnection connection) {
        this.connection = connection;
    }
    
    private String Read() throws IOException {
        InputStream input = connection.openInputStream();
        BufferedReader input_reader = new BufferedReader(new InputStreamReader(input));
        String message = input_reader.readLine();
        System.out.println("device data in: " + message);
        return message;
    }
    
    private void Write(String message) throws IOException {
        if(! message.endsWith("\n")) {
            message = message + "\n";
        }
        OutputStream output = connection.openOutputStream();
        PrintWriter output_writer = new PrintWriter(output);
        System.out.println("device data out: " + message);
        output_writer.print(message);
        output_writer.flush();
    }

    private void PrintDeviceInfo() throws IOException {
        RemoteDevice device = RemoteDevice.getRemoteDevice(connection);
        System.out.println("\ndevice name: " + device.getFriendlyName(true));
        System.out.println("device addr: " + device.getBluetoothAddress());
    }
    
    @Override
    public void run() {
        try {
            PrintDeviceInfo();
            String in = Read();
            Write("{" + in + "}");
            connection.close();
        } catch(Exception e) {
        }
    }
}
