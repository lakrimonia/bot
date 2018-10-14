package com.company;

import java.util.Scanner;

public class Agent {

    public void sendBotAnswer(String message) {
        System.out.println(message);
    }

    public String getUserRequest() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

}