package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversationTest {
    private Bot bot;
    private Conversation conversation;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        bot = new Bot();
        conversation = new Conversation(bot.topicContent, bot.questionAnswer);
    }

    @org.junit.jupiter.api.Test
    void testIncorrect() {
        String botAnswer = conversation.handle("АБВГД");
        String expected = bot.topicContent.get("НЕКОРРЕКТНАЯ КОМАНДА");
        assertEquals(expected, botAnswer);
    }

    @org.junit.jupiter.api.Test
    void testState() {
        assertEquals(State.INITIAL, conversation.getState());
        conversation.handle("викторина");
        assertEquals(State.QUIZ, conversation.getState());
    }

    @org.junit.jupiter.api.Test
    void testStateInitial() {
        conversation.handle("викторина");
        assertEquals(State.QUIZ, conversation.getState());
        for (int i = 0; i < 3; i++) {
            conversation.handle("Не знаю");
        }
        assertEquals(State.INITIAL, conversation.getState());
    }

    @org.junit.jupiter.api.Test
    void testUppercase() {
        assertEquals(State.INITIAL, conversation.getState());
        conversation.handle("ВИКТОРИНА");
        assertEquals(State.QUIZ, conversation.getState());
    }

    @org.junit.jupiter.api.Test
    void testBye() {
        String botAnswer = conversation.handle("бот, пока");
        String expected = bot.topicContent.get("ПРОЩАНИЕ");
        assertEquals(expected, botAnswer);
    }

}
