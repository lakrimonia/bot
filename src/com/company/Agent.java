package com.company;

import java.util.Scanner;

class Agent {

    void sendBotAnswer(String message) {
        System.out.println(message);
    }

    String getUserRequest() {
       try(Scanner scan = new Scanner(System.in)){
        return scan.nextLine();
       }
    }

}