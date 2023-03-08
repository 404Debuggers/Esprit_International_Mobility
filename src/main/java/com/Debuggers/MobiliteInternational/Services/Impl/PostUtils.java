package com.Debuggers.MobiliteInternational.Services.Impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PostUtils {
    public static final String BADWORDS_FILENAME = "badwords.txt"; // le nom du fichier contenant les badwords

    public static String filterBadWords(String text) throws IOException {
        String[] badWords = loadBadWords();
        for (String badWord : badWords) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < badWord.length(); i++) {
                sb.append('*');
            }
            text = text.replaceAll("(?i)" + badWord, sb.toString());
        }
        return text;
    }

    public static String[] loadBadWords() throws IOException {
        File file = new File("C:\\Users\\Marwen\\Desktop\\projet PI push\\Esprit_International_Mobility\\src\\main\\resources\\badwords.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line.trim()).append("|");
        }
        reader.close();
        return builder.toString().split("\\|");
    }
}
