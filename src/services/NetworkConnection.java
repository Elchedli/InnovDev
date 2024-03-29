/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shidono
 */
public abstract class NetworkConnection {
    private final ConnectionThread connThread = new ConnectionThread();
    private final Consumer<Serializable> onReceiveCallback;
    public NetworkConnection(Consumer<Serializable> onReceiveCallback){
        this.onReceiveCallback = onReceiveCallback;
        connThread.setDaemon(true);
    }
    
    public void startConnection() throws Exception{
        connThread.start();
    }
    
    public void send(Serializable data) throws Exception{
        connThread.out.writeObject(data);
    }
    
    public void closeConnection() throws Exception{
        connThread.socket.close();
    }
    
    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();
    private class ConnectionThread extends Thread{
        private Socket socket;
        private ObjectOutputStream out;
        @Override
        public void run(){
            try{
               ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                Socket socket = isServer() ? server.accept() : new Socket(getIP(),getPort());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);
                while(true){
                     Serializable data;
                   data = (Serializable) in.readObject();
                     onReceiveCallback.accept(data);
                }
            }   catch (IOException ex) {
                onReceiveCallback.accept("Connection Closed");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NetworkConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
