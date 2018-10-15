package com.company.Cards;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
//F: implement object factory
/**
 * <h1>StandardDeck</h1>
 * This class implements a standard 52 card deck.
 * @author Joe Cook
 * @version 1.0
 * @since 12/10/2018
 */
public class StandardDeck extends Deck{
    /**
     * Creates a standard deck of 52 cards
     * @param filename Filename of the deck art
     * @param cardWidth Number of characters in one line of a card's ascii art
     * @param cardLines number of lines in a card's ascii art
     */
    public StandardDeck(String filename,int cardWidth,int cardLines){
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader); //Used to read in ascii art from a file
            for (int i = 0; i < 4; i++) {//For each suit
                for (int j = 0; j < 13; j++) {//for each rank in that suit
                    cards.add(new Card(i, j, bufferedReader.readLine(),cardWidth,cardLines));
                }
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        Collections.shuffle(cards); //shuffle the deck
    }
}
