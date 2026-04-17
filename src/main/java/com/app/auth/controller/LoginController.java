package com.app.auth.controller;

import com.app.auth.dao.UserDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import static spark.Spark.post;

public abstract class LoginController {
    private static Gson gson = new Gson();
    private static UserDAO userDAO = new UserDAO();

    public static void initRoutes() {
        post("/api/login", (req, res) -> {
            res.type("application/json");

            JsonObject json = gson.fromJson(req.body(), JsonObject.class);
            String email = json.get("email").getAsString();
            String passwordAttempt = json.get("password").getAsString();

            return userDAO.findByEmail(email).map(user -> {
                try {
                    String hashAttempt = user.generateHash(passwordAttempt, user.getSalt());

                    if (hashAttempt.equals(user.getPassword())) {
                        res.status(200);
                        return gson.toJson("Login realizado com sucesso! Bem-vindo " + user.getName());
                    } else {
                        res.status(401);
                        return gson.toJson("Senha incorreta.");
                    }
                } catch (Exception e) {
                    res.status(500);
                    return gson.toJson("Erro interno no servidor.");
                }
            }).orElseGet(() -> {
                res.status(404);
                return gson.toJson("Usuário não encontrado.");
            });
        });
    }
}
