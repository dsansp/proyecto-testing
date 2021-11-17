package com.example.proyectotesting.patterns.behavioral.mediator;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TelegramTest {


    @Test
    public void sendMessageTest(){
        //randomUser = new User(new Telegram(), "name2");
        Telegram telegram = new Telegram();
        AbstractUser user1 = mock(AbstractUser.class);
        String message = "message";

        doNothing().when(user1).receive(anyString());
        telegram.addUser(user1);

        telegram.sendMessage(message, user1);

        verify(user1).receive(message);
    }

    @Test
    @DisplayName("añadir usuario")
   public void addUser() {
        Telegram telegram = new Telegram();
        AbstractUser user = mock(AbstractUser.class);

        int list = telegram.users.size();
        telegram.addUser(user);

        assertTrue(list < telegram.users.size());

    }
    @Test
    @DisplayName("Eliminar usuario")
   public void removeUser() {
        Telegram telegram=new Telegram();

        AbstractUser user1 = new User(telegram, "ismael");
        int list = telegram.users.size();
        telegram.addUser(user1);
        telegram.removeUser(user1);
        assertEquals(list, telegram.users.size());
    }
}
