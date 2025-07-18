package response.errors;

import request.RequestDTO;
import response.GeneralResponseGenerator;
import response.ResponseGenerator;

public class GeneralErrorResponseProvider implements ResponseGenerator {

    private String error;
    private String message;
    private Integer statusCode;
    private String status;

    public GeneralErrorResponseProvider(String error, String message, Integer statusCode, String status) {
        this.error = error;
        this.message = message;
        this.statusCode = statusCode;
        this.status = status;
    }

    @Override
    public String generate(RequestDTO req) {
        String body = """
            {
                "error": "%s",
                "message": "%s"
            }
            """.formatted(error, message);
        return GeneralResponseGenerator.generateResponse(statusCode, status, "application/json", "HttpJava/1.0", body.length(), body);
    }
}
