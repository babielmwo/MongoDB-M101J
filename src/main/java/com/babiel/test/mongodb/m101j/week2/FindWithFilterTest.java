package com.babiel.test.mongodb.m101j.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;

public class FindWithFilterTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

    collection.drop();

    //insert 10 documents with two random integers
    for (int i = 0; i < 10; i++) {
      collection.insertOne(new Document()
          .append("x", new Random().nextInt(2))
          .append("y", new Random().nextInt(100)));
    }

    Bson filter = new Document("x", 0);   //to see in mongod console log:  mongod -vv

    List<Document> all = collection.find(filter).into(new ArrayList<Document>());

    for (Document cur : all) {
      printJson(cur);
    }

    long count = collection.count(filter);
    System.out.println();
    System.out.println(count);
  }
}
