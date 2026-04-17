package com.app.auth.controller;

import com.app.auth.dao.UserDAO;
import com.app.auth.dto.UserResponseDTO;
import com.app.auth.model.User;
import com.app.auth.validator.UserValidator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.UUID;

import static spark.Spark.*;

public abstract class UserController {

    private static final Gson gson = new Gson();
    private static final UserDAO userDAO = new UserDAO();

    public static void initRoutes() {

        path("/api/users", () -> {

            post("/register", (req, res) -> {
                res.type("application/json");

                JsonObject json = gson.fromJson(req.body(), JsonObject.class);
                String name = json.get("name").getAsString();
                String email = json.get("email").getAsString();
                String password = json.get("password").getAsString();

                try {
                    UserValidator.validate(name, email, password);
                } catch (Exception e){
                    halt(400, gson.toJson(e.getMessage()));
                }

                if (userDAO.findByEmail(email).isPresent()) {
                    res.status(400);
                    halt(400, gson.toJson("Erro: Email já cadastrado."));
                }

                User newUser = new User(name, email);
                newUser.setPassword(password);

                userDAO.save(newUser);

                res.status(201);
                return gson.toJson("Usuário " + name + " salvo com sucesso!");
            });

            get("/list", (req, res) -> {
                res.type("application/json");
                return gson.toJson(userDAO.findAll());
            });

            get("/:id", (req, res) -> {
                res.type("application/json");

                try {
                    UUID id = UUID.fromString(req.params(":id"));
                    return userDAO.findById(id)
                            .map(user -> new UserResponseDTO(user))
                            .map(dto -> gson.toJson(dto))
                            .orElseGet(() -> {
                                res.status(404);
                                return gson.toJson("Usuário não encontrado");
                            });
                } catch (IllegalArgumentException e) {
                    res.status(400);
                    return gson.toJson("Formato de UUID inválido");
                }
            });

            delete("/:id", (req, res) -> {
                res.type("application/json");
                UUID id = UUID.fromString(req.params(":id"));

                if (userDAO.deleteById(id)) {
                    return gson.toJson("Usuário removido");
                } else {
                    res.status(404);
                    return gson.toJson("Usuário não encontrado");
                }
            });
        });
    }
}