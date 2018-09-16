package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Quiz {
    private int score;
    private int tries;
    private HashMap<String, String> question_answer;
    private Scanner scanner;

    public Quiz(HashMap<String, String> question_answer, Scanner scanner){
        score = 0;
        tries = 3;
        this.question_answer = question_answer;
        this.scanner = scanner;
    }

    public void launch(){
        quiz: for (String question : question_answer.keySet()){
            System.out.println(question);
            String right_answer = question_answer.get(question);
            while(true){
                String user_answer = scanner.nextLine();
                if (user_answer.equals(right_answer)){
                    score += 100;
                    System.out.println(String.format("Верно! Ты получаешь 100 очков. Твой счёт: %d", score));
                    break;
                }
                else{
                    System.out.println("Неверно!");
                    tries--;
                    if (tries > 0)
                        System.out.println(String.format("У тебя осталось %d попыток. Подумай и ответь ещё раз.", tries));
                    else{
                        System.out.println("У тебя больше не осталось попыток.");
                        break quiz;
                    }
                }
            }
        }
        if(tries > 0)
            System.out.println("Ты правильно ответил на все вопросы! :)");
        System.out.println(String.format("Игра окончена. Твой счёт: %d", score));
    }
}
