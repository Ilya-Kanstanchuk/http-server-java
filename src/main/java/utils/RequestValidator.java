package utils;

import request.RequestDTO;

public class RequestValidator {
    public static Boolean isRequestFull(RequestDTO dto){
        if (dto.getHttpType() == null || dto.getHost() == null || dto.getMethod() == null || dto.getRoute() == null){
            return false;
        }
        if (dto.getMethod().equals("POST") && dto.getBody() == null){
            return false;
        }
        return true;
    }
    public static Boolean isHostExists(String host){
        String port = System.getenv("PORT_NUMBER");
        String host_1 = System.getenv("HOST1_NAME") + ":" + port;
        String host_2 = System.getenv("HOST2_NAME") + ":" + port;
        if (host.equals(host_1) || host.equals(host_2)){
            return true;
        }
        return false;
    }

    public static Boolean isMethodValid(String method){
        if (method.equals("GET") || method.equals("POST")){
            return true;
        }
        return false;
    }
}
