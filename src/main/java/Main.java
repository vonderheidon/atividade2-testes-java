import com.app.auth.controller.LoginController;
import com.app.auth.controller.UserController;

import static spark.Spark.*;

public class Main {

    private static void enableCORS() {
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        });
    }

    public static void main(String[] args) {

        port(8080);
        enableCORS();

        UserController.initRoutes();
        LoginController.initRoutes();

        System.out.println("Servidor rodando em http://localhost:8080");

    }
}
