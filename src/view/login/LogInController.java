package view.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Controller;
import view.ViewHandler;
import viewmodel.login.LogInViewModel;

public class LogInController implements Controller<LogInViewModel> {

    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginResponseLabel;

    private ViewHandler viewHandler;
    private LogInViewModel logInViewModel;

    @Override
    public void init(ViewHandler viewHandler, LogInViewModel logInViewModel){
        Image logo = new Image("file:resources/logo1.png");
        logoImageView.setImage(logo);

        this.viewHandler = viewHandler;
        this.logInViewModel = logInViewModel;

        usernameTextField.textProperty().bindBidirectional(logInViewModel.usernameProperty());
        passwordTextField.textProperty().bindBidirectional(logInViewModel.passwordProperty());

        loginResponseLabel.textProperty().bind(logInViewModel.loginResponseProperty());
        loginButton.disableProperty().bind(logInViewModel.loginButtonDisabled());
    }

    public void logIn(){
        if (logInViewModel.logIn()){
            logInViewModel.clear();
            viewHandler.openView("Menu");
        }
    }

    public void openSignUp(){
        logInViewModel.clear();
        viewHandler.openView("SignUp");
    }

}
