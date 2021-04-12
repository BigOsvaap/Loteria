package model.game.consumer;

import model.game.buffer.Card;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Random;

import static model.game.Constants.boardSize;

public class Board {

    private final LinkedList<Card> cards;
    private boolean[] beans;

    private PropertyChangeSupport propertyChangeSupport;

    public Board(Card[] cards){
        this.cards = initialize(cards);
        beans = new boolean[boardSize];
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addChangeListener(PropertyChangeListener pcl){
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }

    public void removeChangeListener(PropertyChangeListener pcl){
        propertyChangeSupport.removePropertyChangeListener(pcl);
    }

    private LinkedList<Card> initialize(Card[] array){
        LinkedList<Card> cards = new LinkedList<>();
        Random random = new Random();
        while (cards.size()<boardSize){
            int index = random.nextInt(array.length);
            if(!cards.contains(array[index]))
                cards.add(array[index]);
        }
        return cards;
    }

    public boolean containsCard(Card card){
        return cards.contains(card);
    }

    public LinkedList<Card> getCards(){
        return cards;
    }

    public boolean[] getBeans(){
        return beans;
    }

    public void setBean(Card card){
        propertyChangeSupport.firePropertyChange("index",null, cards.indexOf(card));
        beans[cards.indexOf(card)] = true;
    }

    public boolean hasBean(Card card){
        return beans[cards.indexOf(card)];
    }

}
