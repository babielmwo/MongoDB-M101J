package com.babiel.test.mongodb.m101j.week2;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;
import static java.util.Arrays.asList;

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

    Document jones = new Document("name", "Jones")
        .append("age", 25)
        .append("profession", "programmer");

    printJson(smith);
    printJson(jones);

    coll.insertMany(asList(smith, jones));

    printJson(smith);
    printJson(jones);
  }
}
