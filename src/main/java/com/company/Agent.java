package com.company;

import java.util.Scanner;

class Agent {

    private Scanner scan = new Scanner(System.in);

    public void sendBotAnswer(String message) {
        System.out.println(message);
    }

    public String getUserRequest() {
        return scan.nextLine();
    }
}