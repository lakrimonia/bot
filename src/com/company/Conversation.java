package com.company;

import java.io.*;
import java.util.HashMap;

public class Conversation {
    private HashMap<String, String> text;
    private HashMap<String, String> question_answer;

    public Conversation() throws IOException {
        this.text = new HashMap<>();
        this.text.put("ПРИВЕТСТВИЕ", null);
        this.text.put("СПРАВКА", null);
        this.text.put("ПРЕДЛОЖЕНИЕ ПОИГРАТЬ", null);
        this.question_answer = new HashMap<>();
        String[] text = get_text();
        for (int i = 0; i < text.length - 1; i++) {
            if (this.text.containsKey(text[i])) {
                String key = text[i];
                StringBuilder text_sb = new StringBuilder();
                i++;
                while (!text[i].equals("")) {
                    text_sb.append(text[i]);
                    text_sb.append("\r\n");
                    i++;
                }
                this.text.put(key, text_sb.toString());
            } else if (text[i].equals("ВОПРОСЫ")) {
                i++;
                while (!text[i].equals("")) {
                    String[] question_answer = text[i].split("--");
                    this.question_answer.put(question_answer[0], question_answer[1]);
                    i++;
                }
            }
        }
    }

    private String[] get_text() throws IOException {
        File f = new File("C:\\Users\\user\\Desktop\\Учёба\\bot\\text.txt");
        char[] c = new char[(int) f.length()];
        Reader reader = new InputStreamReader(new FileInputStream(f), "UTF-8");
        reader.read(c);
        reader.close();
        String str = new String(c);
        return str.split("\r\n");
    }

    public void start() {
    }
}
