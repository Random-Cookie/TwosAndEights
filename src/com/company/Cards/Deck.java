package com.company.Cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * <h1>Deck</h1>
 * This abstract class provides extra functionality needed for a Deck rather than a CardStack.
 * @author Joe Cook
 * @version 1.1
 * @since 15/10/2018
 */
public abstract class Deck  extends CardStack {
    /**
     * @return One card from the top of the deck
     */
    public Card dealCard(){
        Card deltCard =  cards.get(0);
        cards.remove(0);
        return deltCard;
    }

    /**
     * @param cardsToDeal Number of cards to deal.
     * @return The desired number of cards in the form of a {@literal List<Card>}]
     * @throws NotEnoughCardsException When there is not enough cards to deal.
     */
    public List<Card> dealCards(int cardsToDeal) throws NotEnoughCardsException{
        List<Card> dealtCards = new ArrayList<>();
        for (int i = 0; (i < cardsToDeal);i++){
            if (cards.size() != 0) {
                dealtCards.add(cards.get(0));
            }else{
                throw new NotEnoughCardsException("Out of cards");
            }
        }
        cards.removeAll(dealtCards);
        return dealtCards;
    }

    /**
     * Adds a list of cards back into the deck and shuffles it.
     * Does not add any wildcards used back into the deck.
     * @param restockCards List of cards to be restocked into the deck.
     */
    public void restock(List<Card> restockCards){
        for (Card card:restockCards){
            if (card.getRank() != -1){
                cards.add(card);
            }
        }
        restockCards.clear();
        Collections.shuffle(cards);
    }
}