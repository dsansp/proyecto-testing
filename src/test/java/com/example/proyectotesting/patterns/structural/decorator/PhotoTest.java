package com.example.proyectotesting.patterns.structural.decorator;

import com.example.proyectotesting.patterns.structural.decorator.decoradores.EffectRetro;
import com.example.proyectotesting.patterns.structural.decorator.decoradores.EffectVintage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {
    @Test
    void showRetroTest() {
        Photo selfie = new DigitalPhoto();
        Photo  selfieRetro = new EffectRetro(selfie);
        assertEquals("Normal photo without edit + Retro",selfieRetro.show());
        double precio=selfieRetro.cost();
        assertEquals(35,precio);

    }

    @Test
    void costBaseTest() {
        Photo selfie = new DigitalPhoto();
        double precio=selfie.cost();
        assertEquals(15,precio);


    }
    @Test
    void showVintageTest() {
        Photo selfie = new DigitalPhoto();
        Photo selfieVintage = new EffectVintage(selfie);
        assertEquals("Normal photo without edit + Vintage", selfieVintage.show());
        double precio = selfieVintage.cost();
        assertEquals(30, precio);
    }
    @Test
    void BrightnessTest() {
        Photo selfie = new DigitalPhoto();

        assertNull(selfie.getBrightness());
        selfie.setBrightness(15);
        int brightAfter= selfie.getBrightness();
        assertEquals(15,brightAfter);
    }

    @Test
    void ContrastTest() {
        Photo selfie = new DigitalPhoto();

        assertNull(selfie.getContrast());
        selfie.setContrast(25);
        int contrastAfter= selfie.getContrast();
        assertEquals(25,contrastAfter);
    }

    @Test
    void SaturationTest() {
        Photo selfie = new DigitalPhoto();

        assertNull(selfie.getSaturation());
        selfie.setSaturation(25);
        int satAfter= selfie.getSaturation();
        assertEquals(25,satAfter);
    }


    @Test
    void BlurTest() {
        Photo selfie = new DigitalPhoto();

        assertNull(selfie.getBlur());
        selfie.setBlur(25);
        int blurAfter= selfie.getBlur();
        assertEquals(25,blurAfter);
    }



}