package com.baggonius.gson.common;

import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class TypeTokens {

  public static final TypeToken<List<String>> SIMPLE_LIST_TYPE = new TypeToken<List<String>>() {};
  public static final TypeToken<List<TestObject>> OBJECT_LIST_TYPE = new TypeToken<List<TestObject>>() {};

  public static final TypeToken<Map<String, String>> SIMPLE_MAP_TYPE = new TypeToken<Map<String, String>>() {};
  public static final TypeToken<Map<String, TestObject>> OBJECT_MAP_TYPE = new TypeToken<Map<String, TestObject>>() {};

  public static final TypeToken<Set<String>> SIMPLE_SET_TYPE = new TypeToken<Set<String>>() {};
  public static final TypeToken<Set<TestObject>> OBJECT_SET_TYPE = new TypeToken<Set<TestObject>>() {};

  public static final TypeToken<SortedSet<TestObjectNotComparable>> NON_COMPARABLE_OBJECT_SET_TYPE = new TypeToken<SortedSet<TestObjectNotComparable>>() {};

}
