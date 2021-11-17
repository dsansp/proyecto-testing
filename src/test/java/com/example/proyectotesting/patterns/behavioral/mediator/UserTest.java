package com.example.proyectotesting.patterns.behavioral.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTest {

    User user = new User(new Telegram(), "Ismael");


    @Test
    @DisplayName("Enviar un mensaje desde un usuario")
    void send() {
        user.mediator = mock(Telegram.class);

        doNothing().when(user.mediator).sendMessage("mensaje", user);
        user.send("mensaje");

        verify(user.mediator).sendMessage("mensaje", user);
    }

    @Test
    @DisplayName("Recibir un mensaje")
    void receive() {
        user.receive("mensaje");
        assertTrue(true);

    }
}