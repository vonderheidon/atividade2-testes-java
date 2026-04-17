package com.app.auth.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    void testCT08ValidacaoUsuarioValido() {
        assertDoesNotThrow(() ->
                UserValidator.validate("Jefferson1", "jefferson1@gmail.com", "Senha@123")
        );
    }

    @Test
    void testCT02ValidacaoNomeNulo() {
        Exception exception = assertThrows(Exception.class, () ->
                UserValidator.validate(null, "jefferson2@gmail.com", "Senha@123")
        );
        assertEquals("O nome deve ter pelo menos 3 caracteres.", exception.getMessage());
    }

    @Test
    void testCT03ValidacaoNomeCurto() {
        Exception exception = assertThrows(Exception.class, () ->
                UserValidator.validate("Je", "jefferson3@gmail.com", "Senha@123")
        );
        assertEquals("O nome deve ter pelo menos 3 caracteres.", exception.getMessage());
    }

    @Test
    void testCT04ValidacaoEmailNulo() {
        Exception exception = assertThrows(Exception.class, () ->
                UserValidator.validate("Jefferson4", null, "Senha@123")
        );
        assertEquals("Email inválido.", exception.getMessage());
    }

    @Test
    void testCT05ValidacaoEmailInvalido() {
        Exception exception = assertThrows(Exception.class, () ->
                UserValidator.validate("Jefferson5", "invalido", "Senha@123")
        );
        assertEquals("Email inválido.", exception.getMessage());
    }

    @Test
    void testCT06ValidacaoSenhaNula() {
        Exception exception = assertThrows(Exception.class, () ->
                UserValidator.validate("Jefferson6", "jefferson6@gmail.com", null)
        );
        assertEquals("A senha deve ter pelo menos 8 caracteres.", exception.getMessage());
    }

    @Test
    void testCT07ValidacaoSenhaCurta() {
        Exception exception = assertThrows(Exception.class, () ->
                UserValidator.validate("Jefferson7", "jefferson7@gmail.com", "1234567")
        );
        assertEquals("A senha deve ter pelo menos 8 caracteres.", exception.getMessage());
    }
}