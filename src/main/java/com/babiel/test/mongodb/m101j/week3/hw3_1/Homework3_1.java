package com.babiel.test.mongodb.m101j.week3.hw3_1;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.babiel.test.mongodb.m101j.util.Helpers.Indent.DONT_INDENT;
import static com.babiel.test.mongodb.m101j.util.Helpers.Indent.INDENT;
import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class Homework3_1 {

  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("school");
    MongoCollection<Document> students = db.getCollection("students");

    // Write a program in the language of your choice that will remove
    // the lowest homework score for each student.
    // Since there is a single document for each student containing
    // an array of scores, you will need to update the scores array
    // and remove the homework.

    // Remember, just remove a homework score. Don't remove a quiz or an exam!

    // Hint/spoiler: With the new schema, this problem is a lot harder
    // and that is sort of the point.
    // One way is to find the lowest homework in code and
    // then update the scores array with the low homework pruned.

    //before
    System.out.println("school.students.count() before " + students.count());

    List<Document> scores = Collections.emptyList();
    for (Document student : students.find()) {
      printJson(student, DONT_INDENT);
      scores = (List<Document>) student.get("scores");

      Double lowestScore = null;
      for (Document score : scores) {
        Double scoreValue = score.getDouble("score");
        if (isHomework(score)) {
          printJson(score, INDENT);
          if (lowestScore == null) {
            lowestScore = scoreValue;
          } else {
            if (scoreValue < lowestScore) {
              lowestScore = scoreValue;
            }
          }
        }
      } // for
      System.out.println("lowest homework score " + lowestScore);
    }

    //after
    System.out.println("students.grades.count() after " + students.count());

  }

  private static boolean isHomework(Document score) {
    return score.getString("type").equals("homework");
  }

  private static void removeGradeEntry(MongoCollection<Document> grades, Document lastEntry) {
    Document toRemove = grades.find(eq("_id", lastEntry.getObjectId("_id"))).first();
    System.out.println("would remove: ");
    printJson(toRemove, INDENT);
//    Document removed = grades.findOneAndDelete(eq("_id", lastEntry.getObjectId("_id")));
//    printJson(removed, INDENT);
  }

  private static boolean studentIdChanged(Document lastEntry, Document grade) {
    return lastEntry.containsKey("student_id") && !Objects.equals(lastEntry.getInteger("student_id"), grade.getInteger("student_id"));
  }
}