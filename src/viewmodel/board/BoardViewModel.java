package viewmodel.board;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.game.GameModel;
import model.game.buffer.Card;
import model.game.consumer.Board;

import static model.game.Constants.boardSize;

public class BoardViewModel {

    private GameModel gameModel;

    private Board board;
    private ImageView[] imageViews;

    public BoardViewModel(GameModel gameModel){
        this.gameModel = gameModel;
        imageViews = new ImageView[boardSize];
        for(int i=0;i<boardSize;i++)
            imageViews[i] = new ImageView();

        newBoard();
    }

    public void newBoard(){
        board = gameModel.generateBoard();
        updateImages();
    }

    private void updateImages(){
        int i = 0;
        for (Card card:board.getCards()) {
            imageViews[i].imageProperty().setValue(new Image(card.getImagePath()));
            i++;
        }
    }

    public ImageView[] getImageViews(){
        return imageViews;
    }

}
