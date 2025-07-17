package response;

public class GeneralResponseGenerator {
    public static String generateResponse(int statusCode, String status, String contentType, String serverName, int length, String body){
        return String.format(
                """
                HTTP/1.1 %d %s
                Content-Type: %s; charset=UTF-8
                Server: %s
                Content-Length: %d
                
                %s
                """, statusCode, status, contentType, serverName, length, body
        );
    }
}
