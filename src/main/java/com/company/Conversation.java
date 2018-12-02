package com.company;

import java.util.HashMap;


public class Conversation {
    private State state;
    private Quiz quiz;
    private CommandHandler commandHandler;
    private HashMap<String, String> topicContent; // Basic background information
    private HashMap<String, String> questionAnswer;
    private boolean continueConversation;

    public Conversation(HashMap<String, String> topicContent, HashMap<String, String> questionAnswer) {
        quiz = new Quiz(questionAnswer, topicContent, this);
        commandHandler = new CommandHandler(this);
        state = State.INITIAL;
        this.topicContent = topicContent;
        this.questionAnswer = questionAnswer;
        continueConversation = true;
    }


    String handle(String message) {
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

    String tryHandle(String message) {
        return commandHandler.tryHandleAsCommand(message, state);
    }

    public Quiz getQuiz() {
        return quiz;
    }

    boolean getContinueConversation() {
        return continueConversation;
    }

    public void stop() {
        if (quiz != null)
            quiz.stop();
        continueConversation = false;
    }
}
