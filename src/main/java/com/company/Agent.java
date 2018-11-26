package com.company;

import java.util.Scanner;


//TODO Где модификаторы доступа?
class Agent {

    void sendBotAnswer(String message) {
        System.out.println(message);
    }

    String getUserRequest() {
       Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

}