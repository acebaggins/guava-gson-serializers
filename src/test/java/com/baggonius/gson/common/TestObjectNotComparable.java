package com.baggonius.gson.common;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

public class TestObjectNotComparable {
  private final String stringField;
  private final List<String> stringFields;
  private final boolean booleanField;
  private final int intField;

  public TestObjectNotComparable() {
    this(newBuilder());
  }

  private TestObjectNotComparable(Builder builder) {
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TestObjectNotComparable that = (TestObjectNotComparable) o;
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

    public TestObjectNotComparable build() {
      return new TestObjectNotComparable(this);
    }
  }
}
