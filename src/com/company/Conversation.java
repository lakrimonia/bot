package com.company;

import java.io.*;
import java.util.HashMap;

public class Conversation {
    private HashMap<String, String> key_data; //Basic background information
    private HashMap<String, String> question_answer; 

    public Conversation() throws IOException {
        this.key_data = new HashMap<>();
        this.key_data.put("ПРИВЕТСТВИЕ", null);
        this.key_data.put("СПРАВКА", null);
        this.key_data.put("ПРЕДЛОЖЕНИЕ ПОИГРАТЬ", null);
        this.question_answer = new HashMap<>();
        String[] lines = get_text();
        for (int i = 0; i < lines.length - 1; i++) {
            if (this.key_data.containsKey(lines[i])) {
                String key = lines[i];
                StringBuilder s_builder = new StringBuilder();
                i++;
                while (!lines[i].equals("")) {
                    s_builder.append(lines[i]);
                    s_builder.append("\r\n");
                    i++;
                }
                this.key_data.put(key, s_builder.toString());
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
