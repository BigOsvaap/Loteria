package viewmodel.login;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.mongoDB.UserModel;

public class LogInViewModel {

    private StringProperty username, password, loginResponse;
    private BooleanProperty loginButtonDisabled;

    private UserModel userModel;

    public LogInViewModel(UserModel userModel){
        this.userModel = userModel;

        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        loginResponse = new SimpleStringProperty();

        loginButtonDisabled = new SimpleBooleanProperty(true);

        username.addListener((observableValue, oldValue, newValue) -> onCredentialsChange());
        password.addListener((observableValue, oldValue, newValue) -> onCredentialsChange());
    }

    private void onCredentialsChange() {
        boolean disabled =  username.get() == null      ||
                username.get().equals("")   ||
                password.get() == null      ||
                password.get().equals("");
        loginButtonDisabled.set(disabled);
    }

    public boolean logIn(){
        if (!userModel.loginUser(username.get(), password.get())) {
            loginResponse.set("Your credentials doesn't match with our database");
            return false;
        }
        userModel.saveUser(username.get());
        return true;
    }

    public void clear(){
        username.set("");
        password.set("");
        loginResponse.set("");
    }

    public StringProperty usernameProperty(){
        return username;
    }

    public StringProperty passwordProperty(){
        return password;
    }

    public StringProperty loginResponseProperty(){
        return loginResponse;
    }

    public BooleanProperty loginButtonDisabled(){
        return loginButtonDisabled;
    }

}
