package com.company.Players;

import com.company.Cards.Card;
import java.util.Scanner;

/**
 * <h1>HumanPlayer</h1>
 * This class provides an implementation of the player class driven by a human player.
 * @author Joe Cook
 * @version 1.0
 * @since 12/10/2018
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name){
        super(name);
    }

    @Override
    public Card pickCard(Card currentCard,int twoAcc) {
        //Print current card and players hand
        System.out.print(currentCard.getDisplayableCard());
        for (int i = 0;i < hand.size();i++){
            String out = "";
            for(int j = 0;j < (hand.get(0).getCARD_WIDTH()/2) - 1;j++){
                out += " ";
            }
            out += "(" + (i + 1) + ")";
            System.out.print(String.format("%-" + (hand.get(0).getCARD_WIDTH() + 1) + "s",out).replace(' ','-'));
        }
        System.out.print('\n');
        for (int i = 0; i < hand.get(0).getCARD_LINES(); i++) {
            for (Card card : hand) {
                System.out.print(card.getDisplayLine(i) + ' ');
            }
            System.out.print('\n');
        }
        for (Card card:hand) {
            for (int i = 0;i < card.getCARD_WIDTH() + 1;i++) {
                System.out.print('_');
            }
        }
        System.out.print('\n');
        //Print Menu
        System.out.println("Current Card: " + currentCard.getCardName());
        System.out.println(" 0|Pick up a card");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(String.format("%2d", (i + 1)) + '|' + hand.get(i).getCardName());
        }
        //Get a menu input
        int menuAns = -1;
        boolean validCard = false;
        while (!validCard){
            menuAns = -1;
            while (menuAns < 0 || hand.size() < menuAns) {
                menuAns = getIntInput();
            }
            if (menuAns != 0){
                validCard = validCard(hand.get(menuAns - 1), currentCard, twoAcc);
            }else{
                return new Card(-1,0,"",0,0);
            }
        }
        for (Card card:hand) {
            for (int i = 0;i<card.getCARD_WIDTH() + 1;i++){
                System.out.print('_');
            }
        }
        System.out.print('\n');
        return hand.get(menuAns - 1);
    }

    @Override
    public int pickSuit() {
        System.out.println("Please pick a Suit:");
        System.out.println("0 | Clubs");
        System.out.println("1 | Diamonds");
        System.out.println("2 | Hearts");
        System.out.println("3 | Spades");
        int input = -1;
        while (input < 0 || input > 3){
            input = getIntInput();
        }
        return input;
    }

    private int getIntInput(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            try{
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("Please input a number");
            }
        }
    }
}