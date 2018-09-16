package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Conversation {
    private HashMap<String, String> topic_content; // Basic background information
    private HashMap<String, String> question_answer;
    private Scanner scanner;
    public boolean continue_conversation;

    public Conversation(HashMap<String, String> topic_content, HashMap<String, String> question_answer) {
        scanner = new Scanner(System.in);
        this.topic_content = topic_content;
        this.question_answer = question_answer;
        continue_conversation = true;
    }

    public void start() {
        System.out.print(topic_content.get("ПРИВЕТСТВИЕ"));
        System.out.print(topic_content.get("СПРАВКА"));
        System.out.print(topic_content.get("ПРЕДЛОЖЕНИЕ ПОИГРАТЬ"));
        while (continue_conversation)
            select_theme();
        say_bye();
    }

    private void select_theme() {
        String input = scanner.nextLine().toLowerCase();
        switch (input) {
            case "бот, пока!":
                continue_conversation = false;
                break;
            case "бот, покажи список команд":
                System.out.println(topic_content.get("СПРАВКА"));
                break;
            case "математика":
                System.out.println("Отличный выбор! Математика -- царица наук!");
                Quiz quiz = new Quiz(this, question_answer, scanner);
                quiz.launch();
                if (continue_conversation) {
                    System.out.println("Хочешь поиграть ещё раз? :) Выбери тему:");
                    System.out.println("1. Математика");
                }
                break;
            default:
                System.out.println("Извини, я плохо понял тебя. Так что ты выбираешь?");
                select_theme();
                break;
        }
    }

    private void say_bye() {
        System.out.println("Пока! Жду нашей встречи!");
    }

    public void show_help() {
        System.out.println(topic_content.get("СПРАВКА"));
    }
}
