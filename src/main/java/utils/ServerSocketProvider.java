package utils;

import exception.ServerAddressException;

import java.net.InetAddress;
import java.net.ServerSocket;

public class ServerSocketProvider {
    private final String host = System.getenv("HOST_NUMBER");
    private final Integer port = Integer.parseInt(System.getenv("PORT_NUMBER"));
    private final Integer backlog = Integer.parseInt(System.getenv("BACKLOG_NUMBER"));
    public ServerSocket createServerSocket() throws Exception{
        try {
            InetAddress address = InetAddress.getByName(host);
            return new ServerSocket(port, backlog, address);
        }catch (Exception e){
            throw new ServerAddressException("Failed to create server socket using InetAddress with host: " + host + " " + e);
        }
    }
}
