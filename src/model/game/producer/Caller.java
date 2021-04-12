package model.game.producer;

import model.game.buffer.Card;
import model.game.buffer.Table;

public class Caller implements Runnable{

    private Deck deck;
    private Table table;

    public Caller(Card[] cards, Table table){
        this.deck = new Deck(cards);
        this.table = table;
    }

    @Override
    public void run() {
        int i = 0;
        while (!deck.isEmpty() & !table.hasWinner()){
            i++;
            Card card = deck.getCard();
            table.setCard(i,card);
        }
        synchronized (table){
            table.notifyAll();
        }
        System.out.println("CALLER: EJECUCIÓN TERMINADA");
    }

}
