package com.company;

import com.company.Games.TwosAndEightsGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        boolean play = true;
        while (play) {
            String menuAns = "";
            while (!(menuAns.equals("1") || menuAns.equals("2") || menuAns.equals("3") || menuAns.equals("4"))) {
                System.out.println("Main Menu:");
                System.out.println(" 1| 1 Opponent");
                System.out.println(" 2| 2 Opponents");
                System.out.println(" 3| 3 Opponents");
                System.out.println(" 4| Exit");
                menuAns = reader.next();
            }
            if (Integer.parseInt(menuAns) < 4) {
                int CARD_LINES = 9;
                int CARD_WIDTH = 11;
                String deckfileName = "resources/defaultDeck.txt";
                String wildCardFilename = "resources/wildCards.txt";
                TwosAndEightsGame game = new TwosAndEightsGame(CARD_LINES, CARD_WIDTH, Integer.parseInt(menuAns), deckfileName, wildCardFilename);
                game.playGame();
            } else {
                play = false;
            }
        }
    }
}
