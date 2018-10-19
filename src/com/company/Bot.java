package com.company;


import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Bot {
    HashMap<String, String> topicContent;
    HashMap<String, String> questionAnswer;

    public Bot() {
        topicContent = new HashMap<>();
        this.questionAnswer = new HashMap<>();
        String[] lines = getText("topics.txt");
        for (int i = 0; i < lines.length - 1; i++) {
            if (lines[i].charAt(0) == '#') {
                String topic = lines[i].substring(1);
                StringBuilder content = new StringBuilder();
                i++;
                while (i < lines.length && !lines[i].equals("")) {
                    content.append(lines[i]);
                    content.append("\r\n");
                    i++;
                }
                this.topicContent.put(topic, content.toString());
            }
        }

        lines = getText("questions.txt");
        for (int i = 1; i < lines.length - 1; i++) {
            while (i < lines.length && !lines[i].equals("")) {
                String[] question_answer = lines[i].split("--");
                this.questionAnswer.put(question_answer[0], question_answer[1]);
                i++;
            }
        }
    }

    private String[] getText(String name) {
        File file = new File(name);
        StringBuilder data = new StringBuilder();
        try {
            Scanner in = new Scanner(file);
            while (in.hasNext())
                data.append(in.nextLine()).append("\r\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String str = data.toString();
        return str.split("\r\n");
    }

    void start() {
        Conversation conversation = new Conversation(topicContent, questionAnswer);
        conversation.start();
    }
}
