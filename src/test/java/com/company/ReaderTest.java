package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

public class ReaderTest {
	private Reader reader = new Reader();

	@org.junit.jupiter.api.BeforeEach
	void setUp() {

	}

	@org.junit.jupiter.api.Test
	void makeTopicContent() {
		HashMap<String, String> topicContent = reader.makeTopicContent();
		assertEquals(10, topicContent.size());
		assertEquals("Пока! Жду нашей встречи!\r\n", topicContent.get("ПРОЩАНИЕ"));
	}

	@org.junit.jupiter.api.Test
	void makeQuestionAnswer() {
		HashMap<String, String> topicContent = reader.makeQuestionAnswer();
		assertEquals(10, topicContent.size());
		assertEquals("64", topicContent.get("8 * 8 = ?"));
	}
}