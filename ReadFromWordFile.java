package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class ReadFromWordFile {

    public static ArrayList<String> arrayOfWordsFromTextFile(String filePath) {
            ArrayList<String> wordArrayFromFile = new ArrayList<String>();
            String word;
            BufferedReader br = null;
            try {

                File file = new File(filePath);
                br = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = br.readLine()) != null) {
                    word = line;
                    if (!word.equals("")){
                        wordArrayFromFile.add(word);
                    }

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                // Always close the BufferedReader
                if (br != null) {
                    try {
                        br.close();
                    }
                    catch (Exception e) {
                    };
                }
            }
            return wordArrayFromFile;
        }
}


