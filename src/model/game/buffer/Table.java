package model.game.buffer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Table {

    private Card card;
    private int numPlayers;
    private int views = 0;
    private boolean winner = false;

    //Añadidos que no queria hacer
    private PropertyChangeSupport propertyChangeSupport;

    public Table(int numPlayers){
        this.numPlayers = numPlayers;
        //cambio malo
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    //cambio malo
    public void addChangeListener(PropertyChangeListener pcl) {
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }

    //cambio malo
    public void removeChangeListener(PropertyChangeListener pcl) {
        propertyChangeSupport.removePropertyChangeListener(pcl);
    }

    public boolean hasWinner(){
        return winner;
    }

    public void setWinner(boolean winner){
        this.winner = winner;
    }

    public synchronized void setCard(int i, Card card){
        propertyChangeSupport.firePropertyChange("card",this.card,card);
        this.card = card;
        System.out.println(i+". Caller: "+card.getId());
        notifyAll();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Card getCard(){
        return card;
    }

    public synchronized void checkView(){
        views++;
        if (views == numPlayers){
            views=0;
            notify();
        }
        if (!winner) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
