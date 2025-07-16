package core;

import exception.ServerAddressException;
import utils.ServerSocketProvider;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private final static Logger LOG = Logger.getLogger(ClientHandler.class.getName());
    private final ServerSocketProvider provider = new ServerSocketProvider();
    private ServerSocket serverSocket;

    /**
     * Starts the server, listens for incoming client connections,
     * and creates a {@link  ClientHandler} for each connected client.
     *
     * @throws ServerAddressException
     */

    public void start(){
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            serverSocket = provider.createServerSocket();
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                executor.submit(clientHandler);
            }
        }catch (Exception e){
            LOG.log(Level.WARNING, "Server exception ", e);
        }
    }
}
