package viewmodel.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.game.GameModel;
import model.game.buffer.Card;
import model.game.consumer.Board;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

import static model.game.Constants.boardSize;

public class GameViewModel implements PropertyChangeListener {

    private GameModel gameModel;

    //Boards
    private Board firstPlayerBoard;
    private Board secondPlayerBoard;
    //Beans
    private ImageView[] beansFirstBoard;
    private ImageView[] beansSecondBoard;

    //Main Card
    private ImageView card;

    private PropertyChangeSupport propertyChangeSupport;

    public GameViewModel(GameModel gameModel) {
        this.gameModel = gameModel;
        gameModel.prepareGame();

        propertyChangeSupport = new PropertyChangeSupport(this);

        firstPlayerBoard = gameModel.getMainPlayerBoard();
        secondPlayerBoard = gameModel.getSecondPlayerBoard();

        beansFirstBoard = new ImageView[boardSize];
        beansSecondBoard = new ImageView[boardSize];
        for(int i=0;i<boardSize;i++){
            beansFirstBoard[i] = new ImageView();
            beansSecondBoard[i] = new ImageView();
        }

        card = new ImageView();

        gameModel.getTable().addChangeListener(this);
        gameModel.getMainPlayer().addChangeListener(this);

        firstPlayerBoard.addChangeListener(propertyChangeEvent -> {
            beansFirstBoard[(int)propertyChangeEvent.getNewValue()].setImage(new Image("file:resources/frijol2.png"));
        });
        secondPlayerBoard.addChangeListener(propertyChangeEvent -> {
            beansSecondBoard[(int)propertyChangeEvent.getNewValue()].setImage(new Image("file:resources/frijol2.png"));
        });

        gameModel.startGame();
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("card")){
            Card card = (Card) propertyChangeEvent.getNewValue();
            Image image = new Image(card.getImagePath());
            this.card.setImage(image);
        }
        if (propertyChangeEvent.getPropertyName().equals("winner")) {
            if ((boolean)propertyChangeEvent.getNewValue())
                propertyChangeSupport.firePropertyChange("playerOneWinner",false,true);
            else
                propertyChangeSupport.firePropertyChange("playerOneWinner",true, false);
        }
    }

    public ImageView getCard(){
        return card;
    }

    public ImageView[] getBeansFirstBoard(){
        return beansFirstBoard;
    }

    public ImageView[] getBeansSecondBoard(){
        return beansSecondBoard;
    }


    public LinkedList<Card> getCardsFirstBoard(){
        return firstPlayerBoard.getCards();
    }

    public LinkedList<Card> getCardsSecondBoard(){
        return secondPlayerBoard.getCards();
    }

    //cambio malo
    public void addChangeListener(PropertyChangeListener pcl) {
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }

    //cambio malo
    public void removeChangeListener(PropertyChangeListener pcl) {
        propertyChangeSupport.removePropertyChangeListener(pcl);
    }

}