package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler {

    private Stage mainStage;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory){
        this.mainStage = new Stage();
        this.viewModelFactory = viewModelFactory;

        mainStage.setTitle("Loteria");
        mainStage.setResizable(false);
    }

    public void start() {
        openView("LogIn");
        mainStage.show();
    }

    public void openView(String viewToOpen){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewToOpen.toLowerCase()+"/"+viewToOpen+"View.fxml"));
        try {
            Parent root = loader.load();
            Controller ctrl = loader.getController();

            if (viewToOpen.equals("LogIn"))
                ctrl.init(this, viewModelFactory.logInViewModel());
            if (viewToOpen.equals("SignUp"))
                ctrl.init(this, viewModelFactory.signUpViewModel());
            if (viewToOpen.equals("Menu"))
                ctrl.init(this, null);
            if (viewToOpen.equals("Board"))
                ctrl.init(this, viewModelFactory.boardViewModel());
            if (viewToOpen.equals("Game"))
                ctrl.init(this, viewModelFactory.gameViewModel());
            if (viewToOpen.equals("History"))
                ctrl.init(this, viewModelFactory.historyViewModel());
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}