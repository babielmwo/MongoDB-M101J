package com.babiel.test.mongodb.m101j.week2.hw2_3;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.babiel.test.mongodb.m101j.util.Helpers.Indent.DONT_INDENT;
import static com.babiel.test.mongodb.m101j.util.Helpers.Indent.INDENT;
import static com.babiel.test.mongodb.m101j.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class Homework2_3 {

  public static void main(String[] args) {
    MongoClient client = new MongoClient();
    MongoDatabase db = client.getDatabase("students");
    MongoCollection<Document> grades = db.getCollection("grades");

    // Write a program in the language of your choice that will
    // remove the grade of type "homework" with the lowest score
    // for each student from the dataset that you imported in the
    // previous homework.
    //
    // Since each document is one grade, it should remove one document per student.

    // Hint/spoiler: If you select homework grade-documents,
    // sort by student and then by score, you can iterate through
    // and find the lowest score for each student by noticing a change in student id.
    //
    // As you notice that change of student_id, remove the document.

    //before
    System.out.println("students.grades.count() before " + grades.count());

    Bson filter = eq("type", "homework");
    Bson sort = and(ascending("student_id"), descending("score"));

    List<Document> homeworks = grades.find(filter)
        .sort(sort)
        .into(new ArrayList<Document>());

    System.out.println("homeworks.size " + homeworks.size());

    Document prevGradeEntry = new Document();
    for (Document grade : homeworks) {
      printJson(grade, DONT_INDENT);

      if (studentIdChanged(prevGradeEntry, grade)) {
        removeGradeEntry(grades, prevGradeEntry);

      }
      prevGradeEntry = grade;
    }
    //last
    removeGradeEntry(grades, prevGradeEntry);

    //after
    System.out.println("students.grades.count() after " + grades.count());

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