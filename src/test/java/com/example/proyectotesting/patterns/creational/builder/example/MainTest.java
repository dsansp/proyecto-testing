package com.example.proyectotesting.patterns.creational.builder.example;

import com.example.proyectotesting.patterns.structural.adapter.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @BeforeEach
    void setUp() {

    }

    @Test
    void mainTest() {

        Main.main(new String[] {});
    }

}