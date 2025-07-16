package core;

import java.net.ServerSocket;

public class Server {
    private final String host = System.getenv("HOST_NUMBER");

    private final ServerSocket serverSocket= new ServerSocket();
}
