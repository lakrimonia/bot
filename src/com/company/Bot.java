package com.company;

import java.io.*;
import java.util.HashMap;

public class Bot {
    public HashMap<String, String> topic_content;
    public HashMap<String, String> question_answer;

    public Bot() throws IOException {
        topic_content = new HashMap<>();
        topic_content.put("ПРИВЕТСТВИЕ", null);
        topic_content.put("СПРАВКА", null);
        topic_content.put("ПРЕДЛОЖЕНИЕ ПОИГРАТЬ", null);
        topic_content.put("НЕКОРРЕКТНАЯ КОМАНДА", null);
        topic_content.put("ПРОЩАНИЕ", null);
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
        Conversation conversation = new Conversation(topic_content, question_answer);
        conversation.start();
    }
}
