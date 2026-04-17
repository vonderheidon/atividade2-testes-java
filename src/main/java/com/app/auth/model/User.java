package com.app.auth.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private String email;
    private transient String password;
    private transient byte[] salt;

    public User(String name, String email){
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public byte[] getSalt() { return salt; }

    public String generateHash(String plainPassword, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
        return Arrays.toString(hashedPassword);
    }

    public void setPassword(String plainPassword) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        this.salt = new byte[16];
        random.nextBytes(this.salt); // Gera salt fixo para ESTE usuário
        this.password = this.generateHash(plainPassword, this.salt);
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
