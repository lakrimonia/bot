package com.company;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversationTest {
    private Bot bot;
    private Conversation conversation;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        HashMap<String, String> topicContent = new HashMap<String, String>();
        topicContent.put("НЕКОРРЕКТНАЯ КОМАНДА", "Извини, я плохо понял тебя.");
        topicContent.put("НЕВЕРНЫЙ ОТВЕТ", "Неверно!");
        topicContent.put("ВЕРНЫЙ ОТВЕТ", "Верно!");
        topicContent.put("КОЛИЧЕСТВО ПОПЫТОК", "Количество оставшихся попыток: %d");
        topicContent.put("ПРОЩАНИЕ", "Пока! Жду нашей встречи!\r\n");
        HashMap<String, String> questionAnswer = new HashMap<String, String>();
        questionAnswer.put("10 + 10 = ?", "20");
        bot = new Bot(topicContent, questionAnswer);
        conversation = new Conversation(bot.getTopicContent(), bot.getQuestionAnswer());
    }

    @org.junit.jupiter.api.Test
    void testIncorrect() {
        String botAnswer = conversation.handle("АБВГД");
        String expected = bot.getTopicContent().get("НЕКОРРЕКТНАЯ КОМАНДА");
        assertEquals(expected, botAnswer);
    }

    @org.junit.jupiter.api.Test
    void testState() {
        assertEquals(State.INITIAL, conversation.getState());
        conversation.handle("бот, викторина");
        assertEquals(State.QUIZ, conversation.getState());
    }

    @org.junit.jupiter.api.Test
    void testStateInitial() {
        conversation.handle("бот, викторина");
        assertEquals(State.QUIZ, conversation.getState());
        for (int i = 0; i < 3; i++) {
            conversation.handle("Не знаю");
        }
        assertEquals(State.INITIAL, conversation.getState());
    }

    @org.junit.jupiter.api.Test
    void testUppercase() {
        assertEquals(State.INITIAL, conversation.getState());
        conversation.handle("БОТ, ВИКТОРИНА");
        assertEquals(State.QUIZ, conversation.getState());
    }

    @org.junit.jupiter.api.Test
    void testBye() {
        String botAnswer = conversation.handle("бот, пока");
        String expected = bot.getTopicContent().get("ПРОЩАНИЕ");
        assertEquals(expected, botAnswer);
    }

}
