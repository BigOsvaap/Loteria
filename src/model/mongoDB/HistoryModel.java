package model.mongoDB;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import model.Partida;
import model.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class HistoryModel {

    private MongoCollection<Document> collection;
    private List<Partida> partidas;

    public HistoryModel(MongoCollection<Document> collection){
        this.collection = collection;
    }

    public void loadHistory(){
        partidas = new ArrayList<>();
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                String username = (String)document.get("username");
                String fecha = (String)document.get("fecha");
                String hora = (String)document.get("hora");
                String puntaje = String.valueOf(document.get("puntaje"));
                Partida temp = new Partida(username,fecha,hora,puntaje);
                partidas.add(temp);
                System.out.println(document.toJson());
            }
        };
        collection.find(eq("username", User.username)).forEach(printBlock);
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

}
