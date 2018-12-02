package com.company.command;

import com.company.Conversation;
import com.company.State;

public class StartQuiz implements ICommand {
    private String description;
    private String userRequest;
    private String botAnswer;
    private Conversation conversation;
    private State state = State.INITIAL;

    public StartQuiz(Conversation conversation) {
        description = "запуск математической викторины.";
        userRequest = "бот, викторина";
        StringBuilder botAnswerSB = new StringBuilder();
        botAnswerSB.append("Отличный выбор! Математика -- царица наук!\r\n");
        botAnswerSB.append("Я буду задавать тебе вопросы по определённой теме, а ты будешь отвечать.\r\n");
        botAnswerSB.append("Ты можешь ответить неправильно не больше 3-х раз.\r\n");
        botAnswerSB.append("За каждый правильный ответ ты будешь получать 100 очков. Посмотрим, сколько ты наберешь :)\r\n");
        botAnswer = botAnswerSB.toString();
        this.conversation = conversation;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUserRequest() {
        return userRequest;
    }

    @Override
    public String getBotAnswer() {
        return botAnswer + ("\n" + conversation.getQuiz().getQuestion());
    }

    @Override
    public String execute() {
        conversation.initializeQuiz();
        return getBotAnswer();
    }
}
