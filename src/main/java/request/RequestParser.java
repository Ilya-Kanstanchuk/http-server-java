package request;

import response.ResponseGenerator;
import response.errors.GeneralErrorResponseProvider;
import utils.RequestValidator;

import java.io.BufferedReader;

public class RequestParser {
    /**
     * Method take {@link BufferedReader} object as a parameter, read line by line,
     * extracting information and return generated  {@link RequestDTO} object
     * @param reader
     * @return {@link RequestDTO}
     * @throws Exception
     */
    private RequestDTO mapToRequestDTO(BufferedReader reader) throws Exception{
        String line;
        int contentLength = 0;
        RequestDTO req = new RequestDTO();
        if (!(line = reader.readLine()).isEmpty()){
            String[] headerElements = line.split(" ");
            req.setMethod(headerElements[0]);
            req.setRoute(headerElements[1]);
            req.setHttpType(headerElements[2]);
        }
        while (!(line = reader.readLine()).isEmpty()){
            if (line.startsWith("Host:")){
                req.setHost(line.split(":", 2)[1].trim());
            }
            if (line.startsWith("Content-Length: ")){
                contentLength = Integer.parseInt(line.split(":")[1].trim());
            }
        }
        if (contentLength > 0){
            char[] chars = new char[contentLength];
            int totalRead = 0;
            while (totalRead < contentLength){
                int read = reader.read(chars, totalRead, contentLength - totalRead);
                if (read == -1) break;
                totalRead += read;
            }
            req.setBody(new String(chars));
        }
        return req;
    }

    public String parseByHost(BufferedReader reader) throws Exception{
        try {
            RequestDTO req = mapToRequestDTO(reader);
            ResponseGenerator generator;
            if (!RequestValidator.isRequestFull(req)){
                generator = new GeneralErrorResponseProvider("Bad Request", "Request is wrong formatted", 400, "Bad Request");
            }
            generator = RouteHandlers.routes.get(req.getHost()).get(req.getMethod()).get(req.getRoute());
            return generator.generate();
        }catch (Exception e){
            return new GeneralErrorResponseProvider("Internal Server Error", "Server throws an error", 500, "Internal Server Error").generate();
        }
    }
}
