package com.company.Cards;
/**
 * <h1>Card</h1>
 * This class implements a card with suit, rank and Ascii art display.
 * @author Joe Cook
 * @version 1.0
 * @since 12/10/2018
 */
public class Card implements Comparable<Card>{
    private final int suit; //Stores an int from 0-3 to represent suit
    private final int rank; //Stores an int from 0-12 to represent A-K respectively
    private final String displayData; //Holds the Ascii art to display the cards
    private final int CARD_WIDTH; //Holds the number of characters in one line of the cards ascii art
    private final int CARD_LINES; //Holds the number of lines in the cards ascii art
    /**
     * Method to initialise the card.
     * @param suit Set the suit of the card.
     * @param rank Set the rank of the card.
     * @param displayData Set the display data of the card.
     * @param cardWidth Number of characters in one line of a card's ascii art
     * @param cardLines number of lines in a card's ascii art
     */
    public Card(int suit,int rank,String displayData,int cardWidth,int cardLines){
        this.suit = suit;
        this.rank = rank;
        this.displayData = displayData;
        this.CARD_WIDTH = cardWidth;
        this.CARD_LINES = cardLines;
    }

    /**
     * @return Suit of the card (int).
     */
    public int getSuit() {
        return suit;
    }

    /**
     * @return Rank of the card.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Method to return a single line of the ascii art of this card
     * @param line The line number of the line to be returned (indexed starting at 0)
     * @return The required line of Ascii art for the card.
     */
    public String getDisplayLine(int line) {
        //Line indexing starts at 0
        return displayData.substring((CARD_WIDTH * (line)),
                (CARD_WIDTH * (line + 1)));
    }

    /**
     * Method to return the ascii art of the whole card so single cards can be displayed easily.
     * @return The cards Ascii Art
     */
    public String getDisplayableCard(){
        String returnVal = "";
        for (int i = 0; i < CARD_LINES;i++){
            returnVal += (getDisplayLine(i) + '\n');
        }
        return returnVal;
    }

    /**
     * @return The card's name
     */
    public String getCardName(){
        String returnVal = "";//Used to construct the card name
        //Add the rank of the card
        switch (rank){
            case -1: returnVal += "Wildcard";
                break;
            case 0: returnVal += "Ace";
                break;
            case 1: returnVal += "Two";
                break;
            case 2: returnVal += "Three";
                break;
            case 3: returnVal += "Four";
                break;
            case 4: returnVal += "Five";
                break;
            case 5: returnVal += "Six";
                break;
            case 6: returnVal += "Seven";
                break;
            case 7: returnVal += "Eight";
                break;
            case 8: returnVal += "Nine";
                break;
            case 9: returnVal += "Ten";
                break;
            case 10: returnVal += "Jack";
                break;
            case 11: returnVal += "Queen";
                break;
            case 12: returnVal += "King";
        }
        //Add the suit of the card
        switch (suit){
            case 0: returnVal += " of Clubs";
                break;
            case 1: returnVal += " of Diamonds";
                break;
            case 2: returnVal += " of Hearts";
                break;
            case 3: returnVal += " of Spades";
        }
        return returnVal;
    }

    /**
     * @return number of lines in the card's ascii art
     */
    public int getCARD_LINES() {
        return CARD_LINES;
    }

    /**
     * @return number of characters in a line of the card's ascii art
     */
    public int getCARD_WIDTH() {
        return CARD_WIDTH;
    }

    /**
     * @param other Another card to compare
     * @return An integer indicating equality with other
     */
    public int compareTo(Card other){
        if (this.suit == other.getSuit()){ //If same suit
            //Compare the rank of the cards
            return Integer.compare(this.rank, other.getRank());
        }else{
            return Integer.compare(this.suit, other.getSuit());
        }
    }
}