package request;

import response.ResponseGenerator;
import response.host1.get.GetHomeRouteResponseGenerator;

import java.util.HashMap;

public class RouteHandlers {
    private static final String HOST_1 = System.getenv("HOST1_NAME");
    private static final String HOST_2 = System.getenv("HOST2_NAME");
    private static final String PORT = System.getenv("PORT_NUMBER");
    public static final HashMap<String, HashMap<String, HashMap<String, ResponseGenerator>>> routes = new HashMap<>();

    static {
        String fullHost1 = HOST_1 + ":" + PORT;
        routes.putIfAbsent(fullHost1, new HashMap<>());
        routes.get(fullHost1).putIfAbsent("GET", new HashMap<>());
        routes.get(fullHost1).get("GET").putIfAbsent("/", new GetHomeRouteResponseGenerator());
    }
}
