package com.baggonius.gson.common;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Objects;

public class TestObject implements Comparable<TestObject> {
  private final String stringField;
  private final List<String> stringFields;
  private final boolean booleanField;
  private final int intField;

  public TestObject() {
    this(newBuilder());
  }

  private TestObject(Builder builder) {
    stringField = builder.stringField;
    stringFields = builder.stringFields;
    booleanField = builder.booleanField;
    intField = builder.intField;
  }

  public String getStringField() {
    return stringField;
  }

  public List<String> getStringFields() {
    return stringFields;
  }

  public boolean isBooleanField() {
    return booleanField;
  }

  public int getIntField() {
    return intField;
  }

  @Override
  public int compareTo(TestObject o) {
    return Ints.compare(intField, o.intField);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TestObject that = (TestObject) o;
    return booleanField == that.booleanField &&
      intField == that.intField &&
      Objects.equals(stringField, that.stringField) &&
      Objects.equals(stringFields, that.stringFields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stringField, stringFields, booleanField, intField);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("stringField", stringField)
      .add("stringFields", stringFields)
      .add("booleanField", booleanField)
      .add("intField", intField)
      .toString();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static TestObject objectOne() {
    return TestObject.newBuilder()
      .withBooleanField(true)
      .withIntField(1775)
      .withStringField("string!")
      .withStringFields(ImmutableList.of("string one", "string two"))
      .build();
  }

  public static TestObject objectTwo() {
    return TestObject.newBuilder()
      .withBooleanField(false)
      .withIntField(1776)
      .withStringField("string again!")
      .withStringFields(ImmutableList.of("string three", "string four"))
      .build();
  }

  public static final class Builder {
    private String stringField;
    private List<String> stringFields;
    private boolean booleanField;
    private int intField;

    private Builder() {}

    public Builder withStringField(String val) {
      stringField = val;
      return this;
    }

    public Builder withStringFields(List<String> val) {
      stringFields = val;
      return this;
    }

    public Builder withBooleanField(boolean val) {
      booleanField = val;
      return this;
    }

    public Builder withIntField(int val) {
      intField = val;
      return this;
    }

    public TestObject build() {
      return new TestObject(this);
    }
  }
}
