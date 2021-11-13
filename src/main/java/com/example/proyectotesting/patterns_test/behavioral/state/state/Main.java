package com.example.proyectotesting.patterns_test.behavioral.state.state;

import java.time.LocalDateTime;

public class Main {
	
	public static void main(String[] args) {
		
		Order order = new Order(1L, LocalDateTime.now());
		
		order.nextState();
		order.nextState();
		order.previousState();
		order.previousState();
		order.nextState();
		order.nextState();
	}

}
