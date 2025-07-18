package response;

import request.RequestDTO;

public interface ResponseGenerator {
    String generate(RequestDTO req);
}
