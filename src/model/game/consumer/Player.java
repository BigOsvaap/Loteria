package model.game.consumer;

import model.game.buffer.Card;
import model.game.buffer.Table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static model.game.Constants.boardSize;
import static model.game.Constants.waitTime;

public class Player implements Runnable{

    private int id;
    private int score = 0;
    private boolean winner = false;
    private Board board;
    private Table table;

    private PropertyChangeSupport propertyChangeSupport;

    public Player(int id, Board board, Table table){
        this.id = id;
        this.board = board;
        this.table = table;
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public void run() {
        while(score < 16 & !table.hasWinner()){
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Card card = table.getCard();
            if (board.containsCard(card)){
                board.setBean(card);
                score++;
                System.out.println("Player "+id+": SI tengo la carta "+card.getId()+" Score: "+score);
                if (score==boardSize){
                    table.setWinner(true);
                    propertyChangeSupport.firePropertyChange("winner",false,true);
                    winner = true;
                    System.out.println("Player "+id+": ¡Lotería!");
                }
            }else{
                System.out.println("Player "+id+": NO tengo la carta "+card.getId()+" Score: "+score);
            }
            table.checkView();
        }
        if (!winner)
            propertyChangeSupport.firePropertyChange("winner", true, false);
        System.out.println("PLAYER "+id+": EJECUCIÓN TERMINADA."+"\tScore: "+score);
    }

    public int getScore(){
        return score;
    }

    public void addChangeListener(PropertyChangeListener pcl){
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }

}
