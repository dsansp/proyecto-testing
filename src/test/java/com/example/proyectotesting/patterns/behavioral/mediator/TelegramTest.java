package com.example.proyectotesting.patterns.behavioral.mediator;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TelegramTest {

    Telegram telegram;
    AbstractUser randomUser;

    @BeforeEach
    void setUp() {
        telegram = new Telegram();
        randomUser = new User(new Telegram(), "name");
    }

    @Test
    public void sendMessageTest(){
        randomUser = new User(new Telegram(), "name2");
        User mockUser = mock(User.class);
        telegram.addUser(mockUser);

        doNothing().when(mockUser).receive("message to be delivered");
        telegram.sendMessage("message to be delivered", randomUser);

        verify(mockUser).receive("message to be delivered");
    }

    //@Test
    // public void addUserTest(){
    // int numContacts = telegram.users.size();
    //telegram.addUser(user);
    //assertTrue(numContacts < telegram.users.size());
//}
}
