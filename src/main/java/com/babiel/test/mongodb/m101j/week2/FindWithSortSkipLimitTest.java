package com.babiel.test.mongodb.m101j.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class FindWithSortSkipLimitTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> collection = db.getCollection("findWithSortTest");

    collection.drop();

    //insert 100 documents with two random integers
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        collection.insertOne(new Document().append("i", i).append("j", j));
      }
    }

    Bson projection = fields(include("i", "j"), excludeId());
    Bson sort = new Document("i", 1).append("j", -1);

    List<Document> all = collection.find()
        .projection(projection)
        .sort(sort)
        .into(new ArrayList<Document>());

    for (Document cur : all) {
      printJson(cur, false);
    }
  }
}
