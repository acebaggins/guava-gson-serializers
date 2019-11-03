package com.baggonius.gson.optional;

import com.baggonius.gson.common.TestObject;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "unused"})
abstract class OptionalTest {

  static Stream<Arguments> optionalArguments() {
    return Stream.of(
      Arguments.of(null, null),
      Arguments.of(null, TestObject.objectOne()),
      Arguments.of("test-string", null),
      Arguments.of("test-string", TestObject.objectOne())
    );
  }

  static class OptionalTester {
    private final Optional<String> optionalString;
    private final Optional<TestObject> optionalTestObject;

    OptionalTester() {
      this.optionalString = Optional.empty();
      this.optionalTestObject = Optional.empty();
    }

    OptionalTester(String value, TestObject testObject) {
      this.optionalString = Optional.ofNullable(value);
      this.optionalTestObject = Optional.ofNullable(testObject);
    }

    OptionalTester(Optional<String> optionalString, Optional<TestObject> optionalTestObject) {
      this.optionalString = optionalString;
      this.optionalTestObject = optionalTestObject;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OptionalTester that = (OptionalTester) o;
      return Objects.equals(optionalString, that.optionalString) &&
        Objects.equals(optionalTestObject, that.optionalTestObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(optionalString, optionalTestObject);
    }
  }

  @SuppressWarnings("Guava")
  static class GuavaOptionalTester {
    private final com.google.common.base.Optional<String> optionalString;
    private final com.google.common.base.Optional<TestObject> optionalTestObject;

    GuavaOptionalTester() {
      this.optionalString = com.google.common.base.Optional.absent();
      this.optionalTestObject = com.google.common.base.Optional.absent();
    }

    GuavaOptionalTester(String value, TestObject testObject) {
      this.optionalString = com.google.common.base.Optional.fromNullable(value);
      this.optionalTestObject = com.google.common.base.Optional.fromNullable(testObject);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      GuavaOptionalTester that = (GuavaOptionalTester) o;
      return Objects.equals(optionalString, that.optionalString) &&
        Objects.equals(optionalTestObject, that.optionalTestObject);
    }

    @Override
    public int hashCode() {
      return Objects.hash(optionalString, optionalTestObject);
    }
  }
}
