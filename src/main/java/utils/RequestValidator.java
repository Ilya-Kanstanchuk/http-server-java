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
}
