package com.company;

import java.io.*;
import java.util.HashMap;

public class Bot {
	public HashMap<String, String> topicContent;
	public HashMap<String, String> questionAnswer;

	public Bot() throws IOException {
		topicContent = new HashMap<>();
		this.questionAnswer = new HashMap<>();
		String[] lines = getText("topics.txt");
		for (int i = 0; i < lines.length - 1; i++) {
			if (lines[i].charAt(0) == '#') {
				String topic = lines[i].substring(1);
				StringBuilder content = new StringBuilder();
				i++;
				while (!lines[i].equals("")) {
					content.append(lines[i]);
					content.append("\r\n");
					i++;
				}
				this.topicContent.put(topic, content.toString());
			}
		}

		lines = getText("questions.txt");
		for (int i = 1; i < lines.length - 1; i++) {
			while (!lines[i].equals("")) {
				String[] question_answer = lines[i].split("--");
				this.questionAnswer.put(question_answer[0], question_answer[1]);
				i++;
			}
		}
	}

	private String[] getText(String name) throws IOException {
		File file = new File(name);
		char[] c = new char[(int) file.length()];
		try (Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
			reader.read(c);
		}
		String str = new String(c);
		return str.split("\r\n");
	}

	public void start() {
		Conversation conversation = new Conversation(topicContent, questionAnswer);
		conversation.start();
	}
}
