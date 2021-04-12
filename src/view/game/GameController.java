package view.game;


import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import view.Controller;
import view.ImageViewPane;
import view.ViewHandler;
import viewmodel.game.GameViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static model.game.Constants.boardSize;

public class GameController implements Controller<GameViewModel>, PropertyChangeListener {

    @FXML
    private ImageView logoImageView;

    @FXML
    private Label nameFirstPlayer;
    @FXML
    private GridPane boardFirstPlayer;
    @FXML
    private Label scoreFirstPlayer;
    private ImageView[] firstBeansBoard;

    @FXML private ImageView card;

    @FXML
    private Label nameSecondPlayer;
    @FXML
    private GridPane boardSecondPlayer;
    @FXML
    private Label scoreSecondPlayer;
    private ImageView[] secondBeansBoard;

    @FXML
    private Button backMenuButton;
    @FXML
    private Label messageLabel;

    private ViewHandler viewHandler;
    private GameViewModel gameViewModel;

    @Override
    public void init(ViewHandler viewHandler, GameViewModel viewModel) {
        Image logo = new Image("file:resources/yugioh.png");
        logoImageView.setImage(logo);

        this.viewHandler = viewHandler;
        this.gameViewModel = viewModel;

        firstBeansBoard = new ImageView[boardSize];
        secondBeansBoard = new ImageView[boardSize];

        card.imageProperty().bind(gameViewModel.getCard().imageProperty());

        gameViewModel.addChangeListener(this);

        backMenuButton.setOpacity(0);
        backMenuButton.setDisable(true);

        renderBoards();
    }

    private void renderBoards(){
        int cardIndex = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                StackPane stackPane;
                ImageView imageView;
                ImageViewPane imageViewPane;

                firstBeansBoard[cardIndex] = new ImageView();
                firstBeansBoard[cardIndex].imageProperty().bind(gameViewModel.getBeansFirstBoard()[cardIndex].imageProperty());
                stackPane = new StackPane();
                imageView = new ImageView(new Image(gameViewModel.getCardsFirstBoard().get(cardIndex).getImagePath()));
                imageViewPane = new ImageViewPane(imageView);
                stackPane.getChildren().addAll(imageViewPane, firstBeansBoard[cardIndex]);
                boardFirstPlayer.add(stackPane,j,i);

                secondBeansBoard[cardIndex] = new ImageView();
                secondBeansBoard[cardIndex].imageProperty().bind(gameViewModel.getBeansSecondBoard()[cardIndex].imageProperty());
                stackPane = new StackPane();
                imageView = new ImageView(new Image(gameViewModel.getCardsSecondBoard().get(cardIndex).getImagePath()));
                imageViewPane = new ImageViewPane(imageView);
                stackPane.getChildren().addAll(imageViewPane,secondBeansBoard[cardIndex]);
                boardSecondPlayer.add(stackPane,j,i);

                cardIndex++;
            }
        }
    }

    public void backMenu(){
        viewHandler.openView("Menu");
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        backMenuButton.setOpacity(1);
        backMenuButton.setDisable(false);
        if ((boolean)propertyChangeEvent.getNewValue())
            Platform.runLater(() -> messageLabel.setText("¡Has ganado!"));
        else
            Platform.runLater(() -> messageLabel.setText("¡Has perdido!"));
    }
}
