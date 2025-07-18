package response.host2.get;

import request.RequestDTO;
import response.GeneralResponseGenerator;
import response.ResponseGenerator;

import java.util.Random;

public class GetRandomRouteResponseGenerator implements ResponseGenerator {
    @Override
    public String generate(RequestDTO req) {
        int randNumber = new Random().nextInt(1000);
        String body = """
            <!DOCTYPE html>
            <html>
            <head>
               <title>/</title>
            </head>
            <body>
               <h1>Random number</h1>
               <p>Your random number: %s</p>
            </body>
            </html>
            """.formatted(randNumber);
        return GeneralResponseGenerator.generateResponse(200, "OK", "text/html", "HttpJava/1.0", body.length(), body);
    }
}
