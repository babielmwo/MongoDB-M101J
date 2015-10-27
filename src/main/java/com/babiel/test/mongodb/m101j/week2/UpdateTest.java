package com.babiel.test.mongodb.m101j.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jcp.xml.dsig.internal.dom.DOMKeyInfo;

import java.util.ArrayList;
import java.util.List;

import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

public class UpdateTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> collection = db.getCollection("updateTest");

    collection.drop();

    //insert 100 documents with two random integers
    for (int i = 0; i < 8; i++) {
      collection.insertOne(new Document().append("_id", i)
          .append("x", i));
    }

    collection.replaceOne(eq("x", 5), new Document("_id", 5).append("x", 20)
        .append("update", true));

    for (Document cur : collection.find().into(new ArrayList<Document>())) {
      printJson(cur, false);
    }
  }
}
