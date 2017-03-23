package my.example.ampq.web;

public class Request {

    private String message;
    private String routingKey;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    @Override
    public String toString() {
        return "Request{" +
                "message='" + message + '\'' +
                ", routingKey='" + routingKey + '\'' +
                '}';
    }
}