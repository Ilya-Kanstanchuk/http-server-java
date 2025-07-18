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

    /**
     * Method parse {@link RequestDTO} object, handle invalid data using {@link RequestValidator} class
     * and then find appropriate {@link ResponseGenerator} implementation in list provided by
     * {@link RouteHandlers}
     * @param reader
     * @return {@link String}
     * @throws NullPointerException
     */

    public String parseByHost(BufferedReader reader) throws Exception{
        try {
            RequestDTO req = mapToRequestDTO(reader);
            ResponseGenerator generator;
            if (!RequestValidator.isRequestFull(req)){
                generator = new GeneralErrorResponseProvider("400 Bad Request", "Request is wrong formatted", 400, "Bad Request");
            }else if (!RequestValidator.isHostExists(req.getHost()))
            {
                generator = new GeneralErrorResponseProvider("400 Bad Request", "Host doesn't exists", 400, "Bad Request");
            }else if(!RequestValidator.isMethodValid(req.getMethod())){
                generator = new GeneralErrorResponseProvider("403 Forbidden", "Invalid request HTTP method", 403, "Forbidden");
            }else{
                generator = RouteHandlers.routes.get(req.getHost()).get(req.getMethod()).get(req.getRoute());
            }
            if (generator == null){
                generator = new GeneralErrorResponseProvider( "404 Not found", "Route was not found", 404, "Not Found");
            }
            return generator.generate(req);
        }catch (Exception e){
            return new GeneralErrorResponseProvider("500 Internal Server Error", "Server throws an error", 500, "Internal Server Error").generate(null);
        }
    }
}
