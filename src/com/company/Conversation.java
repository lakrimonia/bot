package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Conversation {
	private HashMap<String, String> topic_content; // Basic background information
	private HashMap<String, String> question_answer;
	private Scanner scanner;

	public Conversation() throws IOException {
		scanner = new Scanner(System.in);
		topic_content = new HashMap<>();
		topic_content.put("ПРИВЕТСТВИЕ", null);
		topic_content.put("СПРАВКА", null);
		topic_content.put("ПРЕДЛОЖЕНИЕ ПОИГРАТЬ", null);
		this.question_answer = new HashMap<>();
		String[] lines = get_text();
		for (int i = 0; i < lines.length - 1; i++) {
			if (this.topic_content.containsKey(lines[i])) {
				String key = lines[i];
				StringBuilder s_builder = new StringBuilder();
				i++;
				while (!lines[i].equals("")) {
					s_builder.append(lines[i]);
					s_builder.append("\r\n");
					i++;
				}
				this.topic_content.put(key, s_builder.toString());
			} else if (lines[i].equals("ВОПРОСЫ")) {
				i++;
				while (!lines[i].equals("")) {
					String[] question_answer = lines[i].split("--");
					this.question_answer.put(question_answer[0], question_answer[1]);
					i++;
				}
			}
		}
	}

	private String[] get_text() throws IOException {
		File f = new File("text.txt");
		char[] c = new char[(int) f.length()];
		Reader reader = new InputStreamReader(new FileInputStream(f), "UTF-8");
		reader.read(c);
		reader.close();
		String str = new String(c);
		return str.split("\r\n");
	}

	public void start() {
		System.out.print(topic_content.get("ПРИВЕТСТВИЕ"));
		System.out.print(topic_content.get("СПРАВКА"));
		System.out.print(topic_content.get("ПРЕДЛОЖЕНИЕ ПОИГРАТЬ"));
		select_theme();
	}

	private void select_theme() {
		String input = scanner.nextLine();
		switch (input) {
		case "Бот, пока!":
			say_buy();
			break;
		case "Бот, покажи список команд":
			System.out.print(topic_content.get("СПРАВКА"));
			break;
		case "Математика":
			System.out.print("Отличный выбор! Математика - царица наук!");
			launch_quiz();
			break;
		default:
			System.out.print("Извини, я плохо понял тебя. Так что ты выбираешь?e\n");
			select_theme();
			break;
		}
	}

	private void say_buy() {
		System.out.println("Пока! Жду нашей встречи!");
	}

	private void launch_quiz() {

	}

}
