package com.company.Cards;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//F: implement object factory?
/**
 * <h1>Wildcards</h1>
 * This class stores wild cards that allow any card of a given suit to be played on top of them.
 * @author Joe Cook
 * @version 1.0
 * @since 12/10/2018
 */
public class Wildcards extends Deck{
    /**
     * Creates a single CardStack containing the suit wildcards
     * @param filename Filename of the wildcard art
     * @param cardWidth Number of characters in one line of a card's ascii art
     * @param cardLines number of lines in a card's ascii art
     */
    public Wildcards(String filename,int cardWidth,int cardLines){
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i < 4; i++) {
                    cards.add(new Card(i, -1, bufferedReader.readLine(),cardWidth,cardLines));
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param suit The suit of the desired wildcard
     * @return The wildcard of the given suit
     */
    public Card getWildCard(int suit){
        switch (suit){
            case 0:
                return cards.get(0);
            case 1:
                return cards.get(1);
            case 2:
                return cards.get(2);
            default:
                return cards.get(3);
        }
    }
}
