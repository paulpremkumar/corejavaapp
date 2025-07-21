import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CrudServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/users", new UserHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("✅ Server running at http://localhost:8080/users");
    }

    static class UserHandler implements HttpHandler {
        private final UserDAO dao = new UserDAO();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            if ("GET".equalsIgnoreCase(method)) {
                handleGet(exchange);
            } else if ("POST".equalsIgnoreCase(method)) {
                handlePost(exchange);
            } else if ("DELETE".equalsIgnoreCase(method)) {
                handleDelete(exchange);
            } else if ("PUT".equalsIgnoreCase(method)) {
               handlePut(exchange);
            }else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }

        private void handleGet(HttpExchange exchange) throws IOException {
            List<User> users = dao.getAllUsers();
            String response = "[" + String.join(",", users.stream().map(User::toJSON).toList()) + "]";
            sendResponse(exchange, 200, "application/json", response);
        }

        private void handlePost(HttpExchange exchange) throws IOException {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
            BufferedReader br = new BufferedReader(isr);
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                body.append(line);
            }

            // Very basic JSON parsing
            String json = body.toString();
            System.out.println("Received JSON: " + json);
            String username = extractJson(json, "username");
            String email = extractJson(json, "email");
            String password = extractJson(json, "password");

            User newUser = new User(null, username, email, password);
            boolean success = dao.addUser(newUser);

            String response = success ? "User added" : "Error adding user";
            sendResponse(exchange, success ? 200 : 500, "text/plain", response);
        }

        private void handleDelete(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            Map<String, String> params = queryToMap(query);
            Long id = params.containsKey("id") ? Long.parseLong(params.get("id")) : null;

            boolean success = (id != null && dao.deleteUser(id));
            String response = success ? "User deleted" : "User not found or error";
            sendResponse(exchange, success ? 200 : 404, "text/plain", response);
        }

        private void handlePut(HttpExchange exchange) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
    StringBuilder body = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        body.append(line);
    }

    String json = body.toString();
    try {
        Long id = Long.parseLong(extractJson(json, "id"));
        String username = extractJson(json, "username");
        String email = extractJson(json, "email");
        String password = extractJson(json, "password");

        User updatedUser = new User(id, username, email, password);
        boolean success = dao.updateUser(updatedUser);

        String response = success ? "User updated" : "User not found or error";
        sendResponse(exchange, success ? 200 : 404, "text/plain", response);
    } catch (Exception e) {
        sendResponse(exchange, 400, "text/plain", "Invalid JSON input");
    }
}


        private void sendResponse(HttpExchange exchange, int status, String contentType, String response) throws IOException {
            exchange.getResponseHeaders().add("Content-Type", contentType);
            exchange.sendResponseHeaders(status, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private String extractJson(String json, String key) {
    String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]*)\"";
    java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern);
    java.util.regex.Matcher matcher = regex.matcher(json);
    if (matcher.find()) {
        return matcher.group(1);
    }
    return null;
}

        private Map<String, String> queryToMap(String query) {
            Map<String, String> result = new HashMap<>();
            if (query == null) return result;
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                }
            }
            return result;
        }
    }
}
