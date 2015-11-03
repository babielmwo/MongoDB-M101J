package com.babiel.test.mongodb.m101j.util;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;

import static com.babiel.test.mongodb.m101j.util.Helpers.Indent.INDENT;

public class Helpers {
  public enum Indent {DONT_INDENT, INDENT}

  public static void printJson(Document document) {
    printJson(document, INDENT);
  }

  public static void printJson(Document document, Indent indent) {
    JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
        new JsonWriterSettings(JsonMode.SHELL, indent == INDENT));
    new DocumentCodec().encode(jsonWriter, document,
        EncoderContext.builder()
            .isEncodingCollectibleDocument(true)
            .build());
    System.out.println(jsonWriter.getWriter());
    System.out.flush();
  }
}
