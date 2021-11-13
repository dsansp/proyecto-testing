package com.example.proyectotesting.patterns_test.behavioral.state.state;

public interface OrderState {

	void next(Order order);
	
	void previous(Order order);
}
