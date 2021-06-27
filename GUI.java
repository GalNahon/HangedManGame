package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    private static JFrame frame;
    private static JPanel wordToGuess, insertChar, UnusedLetters, gameMode;
    private static JLabel statusOfWord, leftChars;
    private static JButton cmdSubmitLetter, cmdStartNewGame, cmdEndGame;
    private static TextField nextChar;
    private static int numOfHit;
    private static Word currentWord;
    private static PaintComponent paintComponent;
    private static JPanel panel;
    final static String wordArrayFilePath
            = "C:\\mmn13\\src\\com\\company\\WordBank.txt";
    private static final ArrayList<String> wordArrayFromFile = ReadFromWordFile.arrayOfWordsFromTextFile(wordArrayFilePath);


    public GUI(int isFirstGame) {
        currentWord = new Word(wordArrayFromFile);
        numOfHit = 0;
        if (isFirstGame == 1) {
            frame = new JFrame("Hanging Man");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 450);


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        }
        wordToGuess = new JPanel();
        wordToGuess.add(new JLabel("Word to guess: "));
        statusOfWord = new JLabel(Word.getWordStructure());
        wordToGuess.add(statusOfWord);
        panel.add(wordToGuess);

        insertChar = new JPanel();
        cmdSubmitLetter = new JButton("Insert char");
        cmdSubmitLetter.addActionListener(new ButtonListener());
        nextChar = new TextField("", 2);
        insertChar.add(cmdSubmitLetter);
        insertChar.add(nextChar);
        panel.add(insertChar);

        UnusedLetters = new JPanel();
        UnusedLetters.add(new JLabel("Unused letters: "));
        leftChars = new JLabel(currentWord.GetRestOfCharList());
        UnusedLetters.add(leftChars);
        panel.add(UnusedLetters);

        gameMode = new JPanel();
        cmdStartNewGame = new JButton("Start new game");
        cmdStartNewGame.addActionListener(new GUI.ButtonListener());
        gameMode.add(cmdStartNewGame);
        cmdEndGame = new JButton("End game");
        cmdEndGame.addActionListener(new GUI.ButtonListener());
        gameMode.add(cmdEndGame);

        paintComponent = new PaintComponent();
        frame.add(panel, BorderLayout.NORTH);
        frame.add(paintComponent, BorderLayout.CENTER);
        frame.add(gameMode, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void clearPanel() {
        Word.clearListsForNewGame();
        Word.clearWordStructureForNewGame();
        frame.revalidate();
        frame.repaint();
        panel.removeAll();
        panel.revalidate();
        PaintComponent.clearPanel = 1;
        panel.repaint();
    }

    private static class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cmdSubmitLetter) {
                String wordStructure;
                String s = nextChar.getText();
                if (s.length() == 1) {
                    numOfHit += 1;
                    char letterToCheck = Character.toLowerCase(s.charAt(0));
                    if (Word.isLetterLegal(letterToCheck) == 1) {
                        if (Word.isLetterInSelectedCharList(letterToCheck) == 0) {
                            int res = currentWord.isLetterInWord(letterToCheck);
                            if (res == 1) {
                                wordStructure = Word.rebuildWordStructure(letterToCheck);
                            } else wordStructure = Word.getWordStructure();
                            if (!paintComponent.isEndOfGame() && res == 0) {
                                paintComponent.drawNextLineInMan();
                            }
                            leftChars.setText(currentWord.GetRestOfCharList());
                            statusOfWord.setText(wordStructure);
                        }
                    }
                    paintComponent.repaint();

                    if (currentWord.isWordFound()) {
                        JOptionPane.showMessageDialog(null, "Congratulation!!\nYou finish the game after: " + numOfHit + " attempts.", "End of game Word: " + Word.getWord(), JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (paintComponent.isEndOfGame()) {
                        JOptionPane.showMessageDialog(null, "The man was hanged,\n therefore you lost.\nThe Word was " + Word.getWord(), "Game Over", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pleas Insert only one legal letter.", "Bad Input", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == cmdStartNewGame) {
                clearPanel();
                new GUI(0);
            }

            if (e.getSource() == cmdEndGame)
                System.exit(0);

        }

    }
}
