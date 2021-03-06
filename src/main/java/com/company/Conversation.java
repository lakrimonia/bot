package com.company;

import java.util.HashMap;


public class Conversation {
    private State state;
    private Quiz quiz;
    private CommandHandler commandHandler;
    private HashMap<String, String> topicContent; // Basic background information
    private HashMap<String, String> questionAnswer;

    public Conversation(HashMap<String, String> topicContent, HashMap<String, String> questionAnswer) {
        quiz = new Quiz(questionAnswer, topicContent, this);
        commandHandler = new CommandHandler(this);
        state = State.INITIAL;
        this.topicContent = topicContent;
        this.questionAnswer = questionAnswer;
    }


    public String handle(String message) {
        message = message.toLowerCase();
        String answer = null;

        switch (state) {
            case INITIAL:
                answer = commandHandler.tryHandleAsCommand(message, state);
                break;
            case QUIZ:
                answer = quiz.handle(message);
                if (quiz.getIsOver()) {
                    state = State.INITIAL;
                }
        }

        if (answer == null) {
            return topicContent.get("НЕКОРРЕКТНАЯ КОМАНДА");
        }
        return answer;
    }

    public State getState() {
        return state;
    }

    public void initializeQuiz() {
        quiz = new Quiz(questionAnswer, topicContent, this);
        state = State.QUIZ;
    }

    public String tryHandle(String message) {
        return commandHandler.tryHandleAsCommand(message, state);
    }

    public Quiz getQuiz() {
        return quiz;
    }
}
