package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Quiz {
    private int score;
    private int tries;
    private HashMap<String, String> question_answer;
    private Scanner scanner;
    private int right_answers_count;
    private Conversation conversation;

    public Quiz(Conversation conversation, HashMap<String, String> question_answer, Scanner scanner) {
        score = 0;
        tries = 3;
        this.question_answer = question_answer;
        this.scanner = scanner;
        this.conversation = conversation;
        right_answers_count = 0;
    }

    public void launch() {
        quiz:
        for (String question : question_answer.keySet()) {
            String right_answer = question_answer.get(question);
            while (true) {
                System.out.println(question);
                String user_answer = scanner.nextLine().toLowerCase();
                if (user_answer.equals(right_answer)) {
                    right_answers_count++;
                    score += 100;
                    System.out.println(String.format("Верно! Ты получаешь 100 очков. Твой счёт: %d", score));
                    break;
                } else if (user_answer.equals("выход"))
                    break quiz;
                else if (user_answer.equals("бот, пока!")) {
                    conversation.continue_conversation = false;
                    break quiz;
                } else if (user_answer.equals("бот, покажи список команд")) {
                    conversation.show_help();
                } else {
                    System.out.println("Неверно!");
                    tries--;
                    if (tries > 0)
                        System.out.println(String.format("У тебя осталось %d попыток. Подумай и ответь ещё раз.", tries));
                    else {
                        System.out.println("У тебя больше не осталось попыток.");
                        break quiz;
                    }
                }
            }
        }
        if (right_answers_count == question_answer.size())
            System.out.println("Ты правильно ответил на все вопросы! :)");
        System.out.println(String.format("Игра окончена. Твой счёт: %d", score));
    }
}
