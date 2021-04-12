package view.board;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import view.Controller;
import view.ImageViewPane;
import view.ViewHandler;
import viewmodel.board.BoardViewModel;

import static model.game.Constants.boardSize;

public class BoardController implements Controller<BoardViewModel> {

    @FXML
    private GridPane boardGridPane;

    private ImageView[] imageViews;

    private ViewHandler viewHandler;
    private BoardViewModel boardViewModel;

    @Override
    public void init(ViewHandler viewHandler, BoardViewModel boardViewModel) {
        this.viewHandler = viewHandler;
        this.boardViewModel = boardViewModel;

        imageViews = new ImageView[boardSize];
        for(int i=0;i<boardSize;i++)
            imageViews[i] = new ImageView();

        renderBoard();
    }

    private void renderBoard(){
        int index = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                StackPane stackPane = new StackPane();
                imageViews[index].imageProperty().bind(boardViewModel.getImageViews()[index].imageProperty());
                ImageViewPane imageViewPane = new ImageViewPane(imageViews[index]);
                stackPane.getChildren().add(imageViewPane);
                boardGridPane.add(stackPane,j,i);
                index++;
            }
        }
    }

    public void generateBoard(){
        boardViewModel.newBoard();
    }

    public void continueGame(){
        viewHandler.openView("Game");
    }

}
