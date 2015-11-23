package com.babiel.test.mongodb.m101j.week6;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;
import static java.util.Arrays.asList;

public class ReplicaSetTest {
  public static void main( String[] args ) throws InterruptedException {
    MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

    MongoCollection<Document> collection = client.getDatabase("course").getCollection("replication");
    collection.drop();

    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      collection.insertOne(new Document("_id", 1));
      System.out.println("Inserted document: " + i);
      Thread.sleep(500);
    }
  }
}
