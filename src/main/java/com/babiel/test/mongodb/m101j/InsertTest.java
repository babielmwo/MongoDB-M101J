package com.babiel.test.mongodb.m101j;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;

public class InsertTest {
  public static void main( String[] args )
  {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> coll = db.getCollection("insertTest");

    coll.drop();;

    Document smith = new Document("name", "Smith")
        .append("age", 30)
        .append("profession", "programmer");

    printJson(smith);

    coll.insertOne(smith);

    printJson(smith);
  }
}
