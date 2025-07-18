package response.host1.post;

import request.RequestDTO;
import response.GeneralResponseGenerator;
import response.ResponseGenerator;
import response.errors.GeneralErrorResponseProvider;
import response.host1.model.UserDTO;
import utils.PostUserUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PostUserRouteResponseGenerator implements ResponseGenerator {
    private final static Logger log = Logger.getLogger(PostUserRouteResponseGenerator.class.getName());

    private final String body = """
            "status": "OK",
            "message": "User was successfully written"
            """;
    @Override
    public String generate(RequestDTO req) {
        try {
            UserDTO user = PostUserUtils.parseBodyToUserDTO(req.getBody());
            PostUserUtils.writeUserInfo(user);
            return GeneralResponseGenerator.generateResponse(200, "OK", "text/html", "HttpJava/1.0", body.length(), body);
        }catch (Exception e){
            log.log(Level.WARNING, "Failed to write user to file", e);
            return new GeneralErrorResponseProvider("400 Bad Request", "Request is wrong formatted", 400, "Bad Request").generate(null);
        }
    }
}
