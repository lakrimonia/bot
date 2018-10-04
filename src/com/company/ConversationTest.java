package com.company;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class ConversationTest {
	private Bot bot;
	private Conversation conversation;
	
	@org.junit.jupiter.api.BeforeEach
	protected void setUp() throws IOException {
		 bot = new Bot();
		 conversation = new Conversation(bot.topicContent, bot.questionAnswer);
	 }
	
	@org.junit.jupiter.api.Test
	public void testHelp(){
		String botAnswer = conversation.handle("бот, покажи список команд");
		String expected = bot.topicContent.get("СПРАВКА");
		assertEquals(expected, botAnswer);
	}
	
	@org.junit.jupiter.api.Test
	public void testUppercase(){
		String botAnswer = conversation.handle("БОТ, ПОКАЖИ СПИСОК КОМАНД");
		String expected = bot.topicContent.get("СПРАВКА");
		assertEquals(expected, botAnswer);
	}
	
	@org.junit.jupiter.api.Test
	public void testIncorrect(){
		String botAnswer = conversation.handle("АБВГД");
		String expected = bot.topicContent.get("НЕКОРРЕКТНАЯ КОМАНДА");
		assertEquals(expected, botAnswer);
	}
	
	@org.junit.jupiter.api.Test
	public void testState(){
		assertEquals(State.INITIAL, conversation.GetState());
		conversation.handle("математика");
		assertEquals(State.QUIZ, conversation.GetState());
	}
	
	@org.junit.jupiter.api.Test
	public void testStateInitial(){
		conversation.handle("математика");
		assertEquals(State.QUIZ, conversation.GetState());
		for (int i = 0; i < 3; i++) {
			conversation.handle("Не знаю");
		}
		assertEquals(State.INITIAL, conversation.GetState());
	}
	
	@org.junit.jupiter.api.Test
	public void testBye(){
		String botAnswer = conversation.handle("бот, пока");
		String expected = bot.topicContent.get("ПРОЩАНИЕ");
		assertEquals(expected, botAnswer);
	}
	
}
