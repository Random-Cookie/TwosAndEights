package com.company.Cards;
import java.util.ArrayList;
import java.util.List;
/**
 * <h1>CardStack</h1>
 * This class implements a Stack of cards for with no specific limits.
 * @author Joe Cook
 * @version 1.0
 * @since 12/10/2018
 */
public class CardStack {
    protected List<Card> cards = new ArrayList<>();//Holds the cards in this stack of cards

    /**
     * @return The cards held in this 'stack'
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * @return The card on top of the 'stack'
     */
    public Card getTopCard(){
        return cards.get(cards.size() - 1);
    }

    /**
     * @param card Card to add to the stack
     */
    public void addCard(Card card){
        cards.add(card);
    }
}
