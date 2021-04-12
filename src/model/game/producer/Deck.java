package model.game.producer;

import model.game.buffer.Card;

import java.util.Random;
import java.util.Stack;

public class Deck {

    private Stack<Card> cards;

    public Deck(Card[] cards) {
        this.cards = shuffle(cards);
    }

    private Stack<Card> shuffle(Card[] cards){
        Stack<Card> deck = new Stack<>();
        Random random = new Random();
        while(deck.size()<cards.length){
            int index = random.nextInt(cards.length);
            if(deck.search(cards[index])==-1)
                deck.push(cards[index]);
        }
        return deck;
    }

    public Card getCard(){
        return cards.pop();
    }

    public boolean isEmpty(){
        return cards.empty();
    }

}
