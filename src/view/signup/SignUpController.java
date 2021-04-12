package view.signup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Controller;
import view.ViewHandler;
import viewmodel.signup.SignUpViewModel;

public class SignUpController implements Controller<SignUpViewModel> {

    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private Label signupResponseLabel;

    @FXML
    private Button signUpButton;

    private ViewHandler viewHandler;
    private SignUpViewModel signUpViewModel;

    @Override
    public void init(ViewHandler viewHandler, SignUpViewModel signUpViewModel){
        Image logo = new Image("file:resources/logo1.png");
        logoImageView.setImage(logo);

        this.viewHandler = viewHandler;
        this.signUpViewModel = signUpViewModel;

        usernameTextField.textProperty().bindBidirectional(signUpViewModel.usernameProperty());
        passwordTextField.textProperty().bindBidirectional(signUpViewModel.passwordProperty());
        confirmPasswordTextField.textProperty().bindBidirectional(signUpViewModel.confirmPasswordProperty());

        signUpButton.disableProperty().bind(signUpViewModel.signUpButtonDisabledProperty());

        signupResponseLabel.textProperty().bind(signUpViewModel.signupResponseProperty());
    }

    public void signUp(){
        if (signUpViewModel.signUp()){
            signUpViewModel.clear();
            viewHandler.openView("LogIn");
        }
    }

    public void backLogin(){
        viewHandler.openView("LogIn");
    }

}
