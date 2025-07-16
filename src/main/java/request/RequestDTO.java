package request;

public class RequestDTO {
    private String method;
    private String route;
    private String httpType;
    private String host;
    private String body;

    public void setMethod(String method) {
        this.method = method;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setHttpType(String httpType) {
        this.httpType = httpType;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getRoute() {
        return route;
    }

    public String getHttpType() {
        return httpType;
    }

    public String getHost() {
        return host;
    }

    public String getBody() {
        return body;
    }

}
