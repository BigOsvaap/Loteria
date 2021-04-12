package viewmodel.signup;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.mongoDB.UserModel;

public class SignUpViewModel {

    private StringProperty username, password, confirmPassword, signupResponse;
    private BooleanProperty signUpButtonDisabled;

    private UserModel userModel;

    public SignUpViewModel(UserModel userModel){
        this.userModel = userModel;

        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        confirmPassword = new SimpleStringProperty();
        signupResponse = new SimpleStringProperty();

        signUpButtonDisabled = new SimpleBooleanProperty(true);

        username.addListener((observableValue, oldValue, newValue) -> onCredentialsChange());
        password.addListener((observableValue, oldValue, newValue) -> onCredentialsChange());
        confirmPassword.addListener((observableValue, oldValue, newValue) -> onCredentialsChange());

    }

    private void onCredentialsChange() {
        boolean disabled =  username.get() == null      ||
                username.get().equals("")   ||
                password.get() == null      ||
                password.get().equals("")   ||
                confirmPassword.get() == null ||
                confirmPassword.get().equals("");
        signUpButtonDisabled.set(disabled);
    }


    public boolean signUp(){
        if (password.get().equals(confirmPassword.get())){
            if (userModel.userRegistered(username.get())){
                signupResponse.set("User already exists!");
                return false;
            }
            else
                return userModel.registerUser(username.get(), password.get());
        }
        signupResponse.set("Passwords doesn't match");
        return false;
    }

    public void clear(){
        username.set("");
        password.set("");
        confirmPassword.set("");
        signupResponse.set("");
    }


    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty confirmPasswordProperty() {
        return confirmPassword;
    }

    public StringProperty signupResponseProperty() {
        return signupResponse;
    }

    public BooleanProperty signUpButtonDisabledProperty() {
        return signUpButtonDisabled;
    }

}
