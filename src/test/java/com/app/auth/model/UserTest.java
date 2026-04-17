package com.app.auth.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testCT01DeveCriarUsuarioValido() {
        User user = new User("Jefferson", "jefferson@gmail.com");

        assertNotNull(user.getId());
        assertEquals("Jefferson", user.getName());
        assertEquals("jefferson@gmail.com", user.getEmail());
    }

    @Test
    void testCT11IdsDevemSerUnicos() {
        User u1 = new User("Jefferson1", "jefferson1@email.com");
        User u2 = new User("Jefferson2", "jefferson2@email.com");

        assertNotEquals(u1.getId(), u2.getId());
    }

    @Test
    void testCT09SenhaDeveSerCriptografada() throws Exception {
        User user = new User("Jefferson3", "jefferson3@gmail.com");
        String senhaPlana = "Senha@123";

        user.setPassword(senhaPlana);

        assertNotNull(user.getPassword());
        assertNotEquals(senhaPlana, user.getPassword());
        assertNotNull(user.getSalt());
    }

    @Test
    void testCT10ToStringNaoDeveExporSenha() throws Exception {
        User user = new User("Jefferson4", "jefferson4@gmail.com");
        user.setPassword("MinhaSenhaSuperSecreta@$#1234");

        String stringDoObjeto = user.toString();

        assertFalse(stringDoObjeto.contains("MinhaSenhaSuperSecreta@$#1234"));
        assertFalse(stringDoObjeto.contains(user.getPassword()));
        assertTrue(stringDoObjeto.contains("Jefferson4"));
        assertTrue(stringDoObjeto.contains("jefferson4@gmail.com"));
    }
}