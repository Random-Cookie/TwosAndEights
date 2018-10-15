package com.company.Players;

import com.company.Cards.Card;

/**
 * <h1>ComputerPlayer</h1>
 * This class provides an implementation of the player class driven by the computer.
 * @author Joe Cook
 * @version 1.0
 * @since 12/10/2018
 */
public class ComputerPlayer extends Player{

    public ComputerPlayer(String name){
        super(name);
    }

    @Override
    public Card pickCard(Card currentCard, int twoAcc) {
        for (Card card:hand){
            if (validCard(card, currentCard, twoAcc)){
                return card;
            }
        }
        return new Card(-1,0,"",0,0);
    }

    @Override
    public int pickSuit() {
        int clubs = 0;
        int diamonds = 0;
        int hearts = 0;
        int spades = 0;
        for (Card card:hand){
            if(card.getRank() != 10){
                switch (card.getSuit()){
                    case 0:
                        clubs++;
                        break;
                    case 1:
                        diamonds++;
                        break;
                    case 2:
                        hearts++;
                        break;
                    case 3:
                        spades++;
                }
            }
        }
        int max = Math.max(Math.max(Math.max(clubs,diamonds),hearts),spades);
        if (max == clubs){
            return 0;
        }else if (max == diamonds){
            return 1;
        }else if (max == hearts){
            return 2;
        }else{
            return 3;
        }
    }
}