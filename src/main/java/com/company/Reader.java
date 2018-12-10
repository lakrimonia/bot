package com.company;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Reader {
	private static Logger logger = Logger.getLogger(Reader.class.getName());

    public HashMap<String, String> makeTopicContent() {
        HashMap<String, String> topicContent = new HashMap<>();
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
                topicContent.put(topic, content.toString());
            }
        }
        return topicContent;
    }

    public HashMap<String, String> makeQuestionAnswer() {
        HashMap<String, String> questionAnswer = new HashMap<>();

        String[] lines = getText("questions.txt");
        for (int i = 1; i < lines.length - 1; i++) {
            while (i < lines.length && !lines[i].equals("")) {
                String[] question_answer = lines[i].split("--");
                questionAnswer.put(question_answer[0], question_answer[1]);
                i++;
            }
        }
        return questionAnswer;
    }

    private String[] getText(String name) {
        File file = new File(name);
        StringBuilder data = new StringBuilder();
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext())
                data.append(in.nextLine()).append("\r\n");
        } catch (IOException ex) {
        	logger.log(Level.ERROR, "Exeption:", ex);
        }
        String str = data.toString();
        return str.split("\r\n");
    }
}
