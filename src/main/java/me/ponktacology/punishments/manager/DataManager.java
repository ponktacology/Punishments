package me.ponktacology.punishments.manager;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import me.ponktacology.punishments.Punishments;
import org.bson.Document;

@Getter
public class DataManager {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;
    private final MongoCollection<Document> punishments;


    public DataManager(Punishments plugin) {
        this.mongoClient = new MongoClient(new MongoClientURI(plugin.getPunishmentsConfig().getMongoLink()));
        this.mongoDatabase = this.mongoClient.getDatabase("punishments");
        this.punishments = this.mongoDatabase.getCollection("bans");
    }
}