package com.company;

import java.util.Scanner;

public class Agent {

    public void print(String message) {
        System.out.println(message);
    }

    public String getMessage() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

}