package response.host1.get;

import response.GeneralResponseGenerator;
import response.ResponseGenerator;

public class GetHomeRouteResponseGenerator implements ResponseGenerator {

    private final String body = """
            <!DOCTYPE html>
            <html>
            <head>
               <title>/</title>
            </head>
            <body>
               <h1>Main page</h1>
               <p>This is the main page</p>
            </body>
            </html>
            """;
    @Override
    public String generate() {
        return GeneralResponseGenerator.generateResponse(200, "OK", "text/html", "HttpJava/1.0", body.length(), body);
    }
}
