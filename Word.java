package com.company;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Word {

        // instance variables
        private static String wordStructure = "_ ";
        private static String word;
        private static ArrayList<Character> unusedCharList = new ArrayList<Character>(); //char array that didn't selected
        private static ArrayList<Character> selectedCharList = new ArrayList<Character>(); //char array that was selected

    static Character[] letterBank = new Character[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w','x' ,'y','z'
    };

        public Word(ArrayList<String> wordArrayFromFile) {

            word = getRandomWordFromList(wordArrayFromFile);
            for (int i = 0; i< word.length() -1; i++){
                wordStructure += "_ ";
            }
            unusedCharList = new ArrayList<Character>(Arrays.asList(letterBank));
            selectedCharList = new ArrayList<Character>();
        }

    private static String getRandomWordFromList(ArrayList<String> wordArrayFromFile){
        Random random = new Random();
        int indexOfRandomWord = random.nextInt(wordArrayFromFile.size());
        return wordArrayFromFile.get(indexOfRandomWord);
    }
    
    public static void clearListsForNewGame(){
        unusedCharList.clear();
        selectedCharList.clear();
    }
    public static void clearWordStructureForNewGame(){
        wordStructure = "";
    }





        public static String getWordStructure() { return wordStructure;}

        public String GetRestOfCharList() {
            return unusedCharList.toString();
        }

        public static String getWord(){
            return word;
        }

        public boolean isWordFound() {return !wordStructure.contains("_");}


    public static int isLetterInSelectedCharList(char letter){
        for (Character character : selectedCharList) {
            if (character == letter){
                JOptionPane.showMessageDialog(null, "You already tried this input, Try a different one","Used Input",JOptionPane.ERROR_MESSAGE);
                return 1;
            }
        }
        return 0;
    }
/**Check if letter is in word and replaces the placeholder in structure with the letter if found. */
        public int isLetterInWord(char letter){
            int letterFoundFlag = 0;
                for (int i = 0; i< word.length(); i++) {
                    if (word.charAt(i) == letter) {
                        letterFoundFlag = 1;
                    }
                }
                if (!selectedCharList.contains(letter)) { selectedCharList.add(letter); }
                if (unusedCharList.contains(letter)) { unusedCharList.remove(unusedCharList.indexOf(letter));}
            return letterFoundFlag;
        }

        public static String rebuildWordStructure(char letter){
            StringBuilder newWordStructure = new StringBuilder(wordStructure);
            for (int i = 0; i< word.length(); i++){
                if (word.charAt(i) == letter )
                    newWordStructure.setCharAt(i*2, letter);
            }
            wordStructure = newWordStructure.toString();
            return wordStructure;
        }

        public static int isLetterLegal(char letter){
            if ((letter >= 'a' && letter <= 'z'))
                return 1;
            else {
                JOptionPane.showMessageDialog(null, "Char must be in range [a-z]","Bad Input",JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }


}

