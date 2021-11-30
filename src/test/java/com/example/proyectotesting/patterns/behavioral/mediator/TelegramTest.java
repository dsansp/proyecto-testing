package com.example.proyectotesting.patterns.behavioral.mediator;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TelegramTest {


    @Test
    public void sendMessageTest(){

        Telegram telegram = new Telegram();
        String message = "message";

        AbstractUser user1 = mock(AbstractUser.class);
        AbstractUser user2 = mock(AbstractUser.class);
        telegram.addUser(user1);
        telegram.addUser(user2);

        // el usuario1 envia mensaje
        telegram.sendMessage(message, user1);
        // el usuario2 lo recibe
        verify(user2).receive(message);
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
        //int list = telegram.users.size();
        telegram.removeUser(user1);
        //assertTrue(list == telegram.users.size());
        assertEquals(0, telegram.users.size());
    }
}
