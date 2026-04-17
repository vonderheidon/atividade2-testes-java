package com.app.auth.validator;

import java.util.regex.Pattern;

public abstract class UserValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static void validate(String name, String email, String password) throws Exception {
        if (name == null || name.trim().length() < 3) {
            throw new Exception("O nome deve ter pelo menos 3 caracteres.");
        }

        if (email == null || !Pattern.matches(EMAIL_REGEX, email)) {
            throw new Exception("Email inválido.");
        }

        if (password == null || password.length() < 8) {
            throw new Exception("A senha deve ter pelo menos 8 caracteres.");
        }
    }
}