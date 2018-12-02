package com.company;

import java.util.HashMap;

public class Bot {
	private HashMap<String, String> topicContent;
	private HashMap<String, String> questionAnswer;
	private HashMap<String, Conversation> conversations;

	public Bot(HashMap<String, String> topicContent, HashMap<String, String> questionAnswer) {
		this.topicContent = topicContent;
		this.questionAnswer = questionAnswer;
		conversations = new HashMap<>();

	}

	public String handleMessage(String message, String chatId) {
		if (!conversations.containsKey(chatId)) {
			conversations.put(chatId, new Conversation(topicContent, questionAnswer));
		}
		return conversations.get(chatId).handle(message);
	}

	public HashMap<String, String> getTopicContent() {
		return topicContent;
	}

	public HashMap<String, String> getQuestionAnswer() {
		return questionAnswer;
	}
}
