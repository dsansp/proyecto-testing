package com.example.proyectotesting.patterns.creational.prototype;

import com.example.proyectotesting.patterns.structural.adapter.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @BeforeEach
    void setUp() {
    }

    @DisplayName("Comprobando el main")
    @Test
    void main() {
        Circle circle;
        Shape forma;
        Main.main(new String[] {});
    }

}