package com.company;

import java.util.Scanner;

class Agent {

	private Scanner scan;

	void sendBotAnswer(String message) {
		System.out.println(message);
	}

	String getUserRequest() {
		//TODO А зачем каждый раз создается scanner?!
    	scan = new Scanner(System.in);
		return scan.nextLine();
	}
}