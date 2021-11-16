package com.example.proyectotesting.patterns.behavioral.observer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    @BeforeEach
    void setUp() {
        System.out.println("Iniciando test de Weather");
    }


    @AfterEach
    void tearDown() {
        System.out.println("Finalizando test de Weather");
    }



    @DisplayName("Comprobando que se añade un observer ")
    @Test
    void addObserverTest(){
        WeatherObserver obs = new Computer();
        Weather weather = new Weather();
        weather.addObserver(obs);
        assertNotNull(weather);

    }

    @DisplayName("Comprobando que se borra el observer")
    @Test
    void removeObserverTest(){
        List<WeatherObserver> observers = new ArrayList<>();
        WeatherObserver obs = new Computer();
        Weather weather = new Weather();
        weather.addObserver(obs);
        assertNotEquals(1, observers.size());
        weather.removeObserver(obs);


    }

    @DisplayName("Comprobando que se cambia el tiempo")
    @Test
    void changeWeatherTest(){
        Weather weather = new Weather();
        weather.changeWeather(WeatherType.SUNNY);
        assertNotNull(weather);


    }

    @Test
    void notifyObserversTest(){
        Weather aemet = new Weather();

        WeatherObserver computer = new Computer();
        aemet.addObserver(computer);
        aemet.addObserver(new SmartPhone());

        aemet.changeWeather(WeatherType.CLOUDY);
        aemet.changeWeather(WeatherType.RAINY);
        aemet.changeWeather(WeatherType.SUNNY);

        aemet.removeObserver(computer);
        aemet.changeWeather(WeatherType.SUNNY);

        assertNotNull(computer);
        assertNotNull(aemet);
    }

}