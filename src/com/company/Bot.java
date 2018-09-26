package com.company;

import java.io.*;
import java.util.HashMap;

public class Bot {
    public HashMap<String, String> topicContent;
    public HashMap<String, String> questionAnswer;

    public Bot() throws IOException {
        topicContent = new HashMap<>();
        topicContent.put("ПРИВЕТСТВИЕ", null);
        topicContent.put("СПРАВКА", null);
        topicContent.put("ПРЕДЛОЖЕНИЕ ПОИГРАТЬ", null);
        topicContent.put("НЕКОРРЕКТНАЯ КОМАНДА", null);
        topicContent.put("ПРОЩАНИЕ", null);
        this.questionAnswer = new HashMap<>();
        String[] lines = getText();
        for (int i = 0; i < lines.length - 1; i++) {
            if (this.topicContent.containsKey(lines[i])) {
                String topic = lines[i];
                StringBuilder content = new StringBuilder();
                i++;
                while (!lines[i].equals("")) {
                    content.append(lines[i]);
                    content.append("\r\n");
                    i++;
                }
                this.topicContent.put(topic, content.toString());
            } else if (lines[i].equals("ВОПРОСЫ")) {
                i++;
                while (!lines[i].equals("")) {
                    String[] question_answer = lines[i].split("--");
                    this.questionAnswer.put(question_answer[0], question_answer[1]);
                    i++;
                }
            }
        }
    }

    private String[] getText() throws IOException {
        File file = new File("text.txt");
        char[] c = new char[(int) file.length()];
        Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
        reader.read(c);
        reader.close();
        String str = new String(c);
        return str.split("\r\n");
    }

    public void start() {
        Conversation conversation = new Conversation(topicContent, questionAnswer);
        conversation.start();
    }
}
