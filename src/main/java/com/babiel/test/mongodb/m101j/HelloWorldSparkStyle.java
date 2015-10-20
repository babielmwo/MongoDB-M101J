package com.babiel.test.mongodb.m101j;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkStyle {
  public static void main(String[] args) {
    Spark.get(new Route("/") {  //Homepage
      @Override
      public Object handle(Request request, Response response) {
        return "Hello World From Spark";
      }
    });
  }
}
