package com.babiel.test.mongodb.m101j.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;

public class DeleteTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> collection = db.getCollection("deleteTest");

    collection.drop();

    //insert 8 documents, with _id set to the value if the loop variable
    for (int i = 0; i < 8; i++) {
      collection.insertOne(new Document().append("_id", i));
    }


    for (Document cur : collection.find().into(new ArrayList<Document>())) {
      printJson(cur, false);
    }
  }
}
