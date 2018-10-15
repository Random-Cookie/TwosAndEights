package com.company.Players;
import com.company.Cards.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * <h1>Player</h1>
 * This abstract Class sets out the requirements for a player and stores the hand.
 * @author Joe Cook
 * @version 1.0
 * @since 12/10/2018
 */
public abstract class Player {
    List<Card> hand = new ArrayList<>();
    private String name;
    public abstract Card pickCard(Card currentCard,int twoAcc);
    public abstract int pickSuit();

    Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void drawCards(List<Card> cards){
        hand.addAll(cards);
        Collections.sort(hand);
    }

    public List<Card> getHand() {
        return hand;
    }

    boolean validCard(Card pickedCard, Card currentCard, int twoAcc) {
        if (currentCard.getRank() == 1 && twoAcc > 1){
            return pickedCard.getRank() == 1;
        }else if (pickedCard.getRank() == 10){
            return true;
        }else{
            return (currentCard.getRank() == pickedCard.getRank() ||
                    currentCard.getSuit() == pickedCard.getSuit());
        }
    }
}