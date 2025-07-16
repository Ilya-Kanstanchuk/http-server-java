package core;

import exception.ServerMessageException;
import request.RequestParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable{
    private final static Logger LOG = Logger.getLogger(ClientHandler.class.getName());
    private final RequestParser parser = new RequestParser();
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * Method listening to clients requests and pass them to {@code mapToRequestDTO()} method
     *
     * @throws java.io.IOException
     */
    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            parser.mapToRequestDTO(reader);
        }catch (Exception e){
            LOG.log(Level.WARNING, "Failed to process message to server ",  e);
        }
        finally {
            closeSocket();
        }
    }
    private void closeSocket(){
        try {
            if (socket != null && !socket.isClosed()){
                socket.close();
            }
        }catch (Exception e){
            LOG.log(Level.WARNING, "Failed to close socket " + socket.getRemoteSocketAddress(), e);
        }
    }
}
