package com.company.Cards;

/**
 * Exception to be thrown when trying to deal cards but there are not enough cards in the deck
 * @author Joe Cook
 * @version 1.0
 * @since 15/10/2018
 */
public class NotEnoughCardsException extends Exception {
    public NotEnoughCardsException(String message){
        super(message);
    }
}
