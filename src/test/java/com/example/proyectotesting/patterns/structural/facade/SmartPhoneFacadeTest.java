package com.example.proyectotesting.patterns.structural.facade;

import com.example.proyectotesting.patterns.structural.facade.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartPhoneFacadeTest {

    SmartPhone smartPhone;

    @BeforeEach
    void setUp() {
        smartPhone = SmartPhoneFacade.startSmartPhone();
        Battery battery = new Battery();
        CPU cpu = new CPU();
        Screen screen = new Screen();
        GPSSensor gpsSensor = new GPSSensor();
        NFCSensor nfcSensor = new NFCSensor();
        List<Sensor> sensors = Arrays.asList(gpsSensor, nfcSensor);
        SmartPhone phone = new SmartPhone(battery, cpu, screen, sensors);
        SmartPhone oneplus = SmartPhoneFacade.startSmartPhone();
    }

    @Test
    void SmartPhoneStartTest() {

        SmartPhone oneplus = SmartPhoneFacade.startSmartPhone();
        assertTrue(oneplus.getOn());
        assertNotNull(oneplus);



    }

    @Test
    void SmartPhoneStopTest() {
        SmartPhone check = SmartPhoneFacade.startSmartPhone();
        check.stop();
        assertFalse(check.getOn());

    }

    @Test
    void batteryTest() {
        Battery firstCheck = SmartPhoneFacade.startSmartPhone().getBattery();
        SmartPhone check = SmartPhoneFacade.startSmartPhone();
        assertNotNull(check);
        Battery otra=new Battery();
        check.setBattery(otra);
        Battery CheckChanges= SmartPhoneFacade.startSmartPhone().getBattery();
        assertNotEquals(CheckChanges,firstCheck);


    }
    @Test
    void CPUTest() {
        SmartPhone check = SmartPhoneFacade.startSmartPhone();
        CPU Cpu1=check.getCpu();
        assertNotNull(check.getCpu());
        check.setCpu(new CPU());
        assertNotEquals(check.getCpu(),Cpu1);



    }
    @Test
    void ScreenTest() {
        SmartPhone check = SmartPhoneFacade.startSmartPhone();
        assertNotNull(check.getScreen());
        System.out.println(check.getScreen());
        Screen change=new Screen();
        check.setScreen(change);
        assertNotEquals(change,check);



    }
    @Test
    void SetOnTest(){
        smartPhone.setOn(false);
        assertFalse(smartPhone.getOn());



    }

    @Test
    void SensorsTest() {
    List<Sensor> sensorCheckList = new ArrayList<Sensor>();
    sensorCheckList.add(new NFCSensor());
    sensorCheckList.add(new GPSSensor());
    smartPhone.setSensors(sensorCheckList);
    assertEquals(sensorCheckList,smartPhone.getSensors());
    }

}