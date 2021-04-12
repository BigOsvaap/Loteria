package model.game;

import com.mongodb.client.MongoCollection;
import model.User;
import model.game.buffer.Card;
import model.game.buffer.Table;
import model.game.consumer.Board;
import model.game.consumer.Player;
import model.game.producer.Caller;
import org.bson.Document;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static model.game.Constants.numCards;
import static model.game.Constants.numPlayers;

public class GameModel implements PropertyChangeListener {

    private Card[] cards;
    private Table table;

    private Caller caller;

    private Player mainPlayer;
    private Player secondPlayer;
    private Board mainPlayerBoard;
    private Board secondPlayerBoard;

    MongoCollection<Document> collection;

    public GameModel(MongoCollection<Document> collection){
        this.collection = collection;
        cards = new Card[numCards];
        for (int i=0;i<numCards;i++)
            cards[i] = new Card(i+1, "file:resources/cards/"+(i+1)+".png");
        System.out.println("EL USUARIO ES: "+ User.username);
    }

    public void prepareGame(){
        secondPlayerBoard = new Board(cards);
        table = new Table(numPlayers);
        caller = new Caller(cards, table);
        mainPlayer = new Player(0, mainPlayerBoard, table);
        secondPlayer = new Player(1, secondPlayerBoard, table);
        mainPlayer.addChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Document doc = new Document("username", User.username)
                .append("fecha", new SimpleDateFormat("dd-MM-yyyy").format(new Date()))
                .append("hora", new SimpleDateFormat("HH:mm").format(new Date()))
                .append("puntaje", mainPlayer.getScore());
        collection.insertOne(doc);
    }

    public void startGame(){
        new Thread(caller).start();
        new Thread(mainPlayer).start();
        new Thread(secondPlayer).start();
    }

    public void saveGame(){

    }

    public Board generateBoard(){
        mainPlayerBoard = new Board(cards);
        return mainPlayerBoard;
    }

    public Table getTable() {
        return table;
    }

    public Player getMainPlayer() {
        return mainPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public Board getMainPlayerBoard() {
        return mainPlayerBoard;
    }

    public Board getSecondPlayerBoard() {
        return secondPlayerBoard;
    }

}
