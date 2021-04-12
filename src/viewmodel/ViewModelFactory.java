package viewmodel;

import model.ModelFactory;
import viewmodel.board.BoardViewModel;
import viewmodel.game.GameViewModel;
import viewmodel.history.HistoryViewModel;
import viewmodel.login.LogInViewModel;
import viewmodel.signup.SignUpViewModel;

public class ViewModelFactory {

    private LogInViewModel logInViewModel;
    private SignUpViewModel signUpViewModel;
    private BoardViewModel boardViewModel;
    private GameViewModel gameViewModel;
    private HistoryViewModel historyViewModel;

    private ModelFactory modelFactory;

    public ViewModelFactory(ModelFactory modelFactory){
        this.modelFactory = modelFactory;
    }

    public LogInViewModel logInViewModel(){
        if (logInViewModel == null)
            logInViewModel = new LogInViewModel(modelFactory.getUserModel());
        return logInViewModel;
    }

    public SignUpViewModel signUpViewModel(){
        if (signUpViewModel == null)
            signUpViewModel = new SignUpViewModel(modelFactory.getUserModel());
        return signUpViewModel;
    }

    public BoardViewModel boardViewModel(){
        if(boardViewModel == null)
            boardViewModel = new BoardViewModel(modelFactory.getGameModel());
        return boardViewModel;
    }

    public GameViewModel gameViewModel(){
        gameViewModel = new GameViewModel(modelFactory.getGameModel());
        return gameViewModel;
    }

    public HistoryViewModel historyViewModel(){
        historyViewModel = new HistoryViewModel(modelFactory.getHistoryModel());
        return historyViewModel;
    }

}
