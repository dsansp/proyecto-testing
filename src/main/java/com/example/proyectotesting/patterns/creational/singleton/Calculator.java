package com.example.proyectotesting.patterns.creational.singleton;

public class Calculator {

	private static com.example.proyectotesting.patterns.creational.singleton.Calculator calculadora;
	
	public Calculator() {
		
	}
	
	public static com.example.proyectotesting.patterns.creational.singleton.Calculator getCalculator(){

		if(calculadora == null)
			calculadora = new com.example.proyectotesting.patterns.creational.singleton.Calculator();
		
		return calculadora;
	}
	
	
	
	
	public int sum(int num1, int num2) {
		return num1 + num2;
	}
}
