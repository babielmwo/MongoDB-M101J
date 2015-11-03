package com.babiel.test.mongodb.m101j.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.babiel.test.mongodb.m101j.util.Helpers.Indent.INDENT;
import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

public class UpdateTest {
  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("course");
    MongoCollection<Document> collection = db.getCollection("updateTest");

    collection.drop();

    //insert 8 documents, with _id set to the value if the loop variable
    for (int i = 0; i < 8; i++) {
      collection.insertOne(new Document().append("_id", i)
          .append("x", i));
    }

//    collection.replaceOne(eq("x", 5), new Document("_id", 5).append("x", 20)
//        .append("update", true));

//    collection.updateOne(eq("_id", 9), new Document("$set", new Document("x", 20)),
//        new UpdateOptions().upsert(true));

    collection.updateMany(gte("_id", 5), new Document("$inc", new Document("x", 1)));

    for (Document cur : collection.find().into(new ArrayList<Document>())) {
      printJson(cur, INDENT);
    }
  }
}
