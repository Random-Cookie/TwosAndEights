package Tests;

import com.company.Cards.Card;
import com.company.Cards.CardStack;
import com.company.Cards.Deck;
import com.company.Cards.StandardDeck;

public class Testing {
    public static void main(String args[]){
        Deck gameDeck = new StandardDeck("defaultDeck.txt",9,11);
        CardStack discardPile = new CardStack();
        for (int i = 0;i < 10;i++){
            discardPile.addCard(gameDeck.dealCard());
        }
        discardPile.addCard(new Card(3,-1,"",0,0));
        gameDeck.restock(discardPile.getCards());
        System.out.println("HALT");
    }
}
