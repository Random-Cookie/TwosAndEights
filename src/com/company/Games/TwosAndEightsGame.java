package com.company.Games;

import com.company.Cards.*;
import com.company.Players.ComputerPlayer;
import com.company.Players.HumanPlayer;
import com.company.Players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
+ | Clubs    (0)
O | Diamonds (1)
# | Hearts   (2)
@ | Spades   (3)
 */
/**
 * <h1>TwosAndEightsGame</h1>
 * This class simulates one game of twos and eights.
 * @author Joe Cook
 * @version 1.1
 * @since 15/10/2018
 */
public class TwosAndEightsGame {
    private List<Player> players; //List of all of the players playing the game
    private Deck gameDeck; //The deck of cards used to play the game
    private CardStack discardPile; //Stores all the cards that have been played
    private Wildcards wildcards; //Stores the wildcards used when a jack is played to allow any card of the chosen suit to be played
    private int twoAcc = 1; //Amount of cards a player draws when they choose to pick up (increases to 2 on first 2 played then += 2 for each two after that)
    private boolean missAGo = false; //Used to make a player miss a go when an eight is played

    /**
     * Sets up the game parameters
     * @param cardLines Number of lines in the card art
     * @param cardWidth Number of characters in one line of card art
     * @param compPlayers Number of computer controlled players in the game
     * @param deckFilename Filename of the deck art file
     * @param wildcardFilename Filename of the wildcard art file
     */
    public TwosAndEightsGame(int cardLines, int cardWidth, int compPlayers, String deckFilename, String wildcardFilename){
        //Initialisation
        players = new ArrayList<>();
        System.out.println("Please input your name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        players.add(new HumanPlayer(name));
        for (int i = 0; i < compPlayers;i++){ //Add the correct number of computer controlled players
            players.add(new ComputerPlayer("Comp " + (i + 1))); //F: Add name randomiser
        }
        this.gameDeck = new StandardDeck(deckFilename, cardWidth, cardLines);
        this.discardPile = new CardStack();
        //Game Setup
        discardPile.addCard(gameDeck.dealCard()); //Add a single card to the discard pile to be the first game card
        for (Player player:players){ //Deal 7 cards to each player
            try {
                player.drawCards(gameDeck.dealCards(7));
            } catch (NotEnoughCardsException ex){
                System.out.print(ex.getMessage());
            }
        }
        //Wildcards used when a jack is played to allow any card of the given suit to be played on top of it
        wildcards = new Wildcards(wildcardFilename, cardWidth, cardLines);
    }

    /**
     * Plays a single game
     */
    public void playGame(){
        while (!checkWin()){ //While no one has won
            for (Player player:players) { //For each player
                if ((!missAGo) && (!checkWin())) { //if an eight want played last turn and no one has won
                    Card pickedCard = player.pickCard(discardPile.getTopCard(), twoAcc); //See player.pickCard
                    if (pickedCard.getSuit() == -1) { //See player.pickCard
                        boolean retry;
                        do {
                            try {
                                player.drawCards(gameDeck.dealCards(twoAcc));
                                retry = false;
                            } catch (NotEnoughCardsException ex) {
                                gameDeck.restock(discardPile.getCards()); //restock the deck from discard pile
                                retry = true;
                            }
                        }while (retry);
                        System.out.println(player.getName() + " Drew " + twoAcc + " cards.");
                        if (discardPile.getTopCard().getRank() == 1) { //If a two was played and a player has drawn reset twoAcc
                            twoAcc = 1;
                        }
                    }else{
                        //Play a card
                        discardPile.addCard(pickedCard);
                        player.getHand().remove(pickedCard);
                        System.out.println(player.getName() + " played the: " + pickedCard.getCardName());
                        //If statement to deal with the special cards
                        if (pickedCard.getRank() == 10) {
                            //If a jack is played the player must pick a suit
                            int suit = player.pickSuit();
                            String suitName;
                            switch (suit){
                                case 0:
                                    suitName = "Clubs";
                                    break;
                                case 1:
                                    suitName = "Diamonds";
                                    break;
                                case 2:
                                    suitName = "Hearts";
                                    break;
                                default:
                                    suitName = "Spades";
                            }
                            System.out.println(player.getName() + " changed the suit to " + suitName);
                            //Add a wildcard to allow any card of the picked suit to be played
                            discardPile.addCard(wildcards.getWildCard(suit));
                        } else if (pickedCard.getRank() == 7) {
                            //If an eight is played missAGo set to true so the next player misses a turn
                            missAGo = true;
                        } else if (pickedCard.getRank() == 1) {
                            //If a two is played set twoAcc to 2 or add 2 if already set to 2
                            twoAcc = (twoAcc == 1) ? twoAcc + 1: twoAcc + 2;
                        }
                    }
                }else{
                    //If a turn is missed reset missAGo to false
                    missAGo = false;
                }
            }
        }
        for (int i = 0; i < getWinner().getName().length() + 18; i++){
            System.out.print('_');
        }
        System.out.print('\n');
        System.out.println(getWinner().getName() +  " has won the game!");
        if (getWinner() instanceof HumanPlayer){
            System.out.println("CONGRATULATIONS YOU WON!");
        }else{
            System.out.println("GAME OVER!");
        }
        for (int i = 0; i < getWinner().getName().length() + 18; i++){
            System.out.print('_');
        }
        System.out.print('\n');
    }

    /**
     * Check if anyone has won the game
     * @return True if someone has won, false otherwise
     */
    private boolean checkWin(){
        for (Player player:players){
            //If a players hand is empty they have won
            if (player.getHand().size() == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Get the winner
     * @return the winning player
     */
    private Player getWinner(){
        for (Player player:players){
            //Return the player who has no cards left in their hand
            if (player.getHand().size() == 0){
                return player;
            }
        }
        return null;
    }
}