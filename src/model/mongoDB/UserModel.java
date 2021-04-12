package model.mongoDB;

import com.mongodb.client.MongoCollection;
import model.User;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class UserModel {

    MongoCollection<Document> collection;

    public UserModel(MongoCollection<Document> collection){
        this.collection = collection;
    }

    public boolean registerUser(String username, String password){
        Document doc = new Document("username", username)
                    .append("password", password);
        collection.insertOne(doc);
        return userRegistered(username);
    }

    public boolean loginUser(String username, String password){
        return collection.find(and(eq("username", username),eq("password",password))).first() != null;
    }

    public void saveUser(String username){
        User.getInstance();
        User.username = username;
    }

    public boolean userRegistered(String username){
        return collection.find(eq("username",username)).first() != null;
    }

}
