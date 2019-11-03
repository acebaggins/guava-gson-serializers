package com.baggonius.gson.common;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;

@SuppressWarnings("UnstableApiUsage")
public class JsonResources {

  public static final String SIMPLE_ARRAY = readJson("simple-array.json");
  public static final String OBJECT_ARRAY = readJson("object-array.json");

  public static final String SIMPLE_MAP = readJson("simple-map.json");
  public static final String OBJECT_MAP = readJson("object-map.json");

  public static final String UNSORTED_SIMPLE_ARRAY = readJson("unsorted-simple-array.json");
  public static final String UNSORTED_OBJECT_ARRAY = readJson("unsorted-object-array.json");

  public static final String UNSORTED_SIMPLE_MAP = readJson("unsorted-simple-map.json");
  public static final String UNSORTED_OBJECT_MAP = readJson("unsorted-object-map.json");

  public static final String OPTIONAL_OBJECT = readJson("optional-object.json");
  public static final String BAD_BI_MAP = readJson("bad-bimap.json");

  public static final String ALL_TOGETHER_NOW = readJson("all-together-now.json");

  private static String readJson(String filename) {
    try {
      return Resources.toString(getResource(filename), Charsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
