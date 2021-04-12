package model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import model.game.GameModel;
import model.mongoDB.HistoryModel;
import model.mongoDB.UserModel;

public class ModelFactory {

    private MongoDatabase database;

    private UserModel userModel;
    private GameModel gameModel;
    private HistoryModel historyModel;

    public ModelFactory(String username, String password){
        MongoClientURI connectionString = new MongoClientURI("mongodb+srv://"+username+":"+password+"@walle-au0k8.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(connectionString);
        database = mongoClient.getDatabase("Loteria");
    }

    public UserModel getUserModel(){
        if (userModel == null)
            userModel = new UserModel(database.getCollection("Users"));
        return userModel;
    }

    public GameModel getGameModel(){
        if (gameModel == null)
            gameModel = new GameModel(database.getCollection("Games"));
        return gameModel;
    }

    public HistoryModel getHistoryModel(){
        if (historyModel == null)
            historyModel = new HistoryModel(database.getCollection("Games"));
        return historyModel;
    }

}
