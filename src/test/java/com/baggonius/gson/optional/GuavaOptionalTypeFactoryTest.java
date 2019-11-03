package com.baggonius.gson.optional;

import com.baggonius.gson.common.JsonResources;
import com.baggonius.gson.common.TestObject;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"JUnit5MalformedParameterized", "Guava"})
@DisplayName("Guava Optional Type Adapter Factory")
class GuavaOptionalTypeFactoryTest extends OptionalTest {
  private static final TypeToken<Optional<String>> SIMPLE_OPTIONAL_TYPE = new TypeToken<Optional<String>>() {};
  private static final TypeToken<Optional<TestObject>> OBJECT_OPTIONAL_TYPE = new TypeToken<Optional<TestObject>>() {};
  private static final TypeToken<Optional> TYPELESS_OPTIONAL_TYPE = new TypeToken<Optional>() {};

  private final Gson gson = new GsonBuilder()
    .registerTypeAdapterFactory(OptionalTypeFactory.forGuava())
    .create();

  @Test
  @DisplayName("Read - Empty optional for JSON null")
  void emptyOptionalForJsonNull() {
    Optional<String> optional = gson.fromJson("null", SIMPLE_OPTIONAL_TYPE.getType());

    assertThat(optional.toJavaUtil()).isEmpty();
  }

  @Test
  @DisplayName("Read - String Optional - Return Optional of string")
  void stringOptionalReturnOptionalOfString() {
    Optional<String> optional = gson.fromJson("\"this is a string\"", SIMPLE_OPTIONAL_TYPE.getType());

    assertThat(optional.toJavaUtil()).contains("this is a string");
  }

  @Test
  @DisplayName("Read - Object Optional - Return Optional of Test Object one")
  void objectOptionalReturnOptionalOfTestObjectOne() {
    Optional<TestObject> optional = gson.fromJson(JsonResources.OPTIONAL_OBJECT, OBJECT_OPTIONAL_TYPE.getType());

    assertThat(optional.toJavaUtil()).contains(TestObject.objectOne());
  }

  @ParameterizedTest(name = "OptionalTest({0}, {1})")
  @DisplayName("Read/Write - Can read/write String optionals")
  @MethodSource("optionalArguments")
  void readWriteCanReadWriteStringOptionals(String string, TestObject testObject) {
    GuavaOptionalTester tester = new GuavaOptionalTester(string, testObject);

    String json = gson.toJson(tester);
    GuavaOptionalTester deserialized = gson.fromJson(json, GuavaOptionalTester.class);

    assertThat(deserialized).isEqualTo(tester);
  }


  @Test
  @DisplayName("Null Fields - Null optional becomes empty optional")
  void nullOptionalBecomesEmptyOptional() {
    Gson gson = new GsonBuilder()
      .registerTypeAdapterFactory(OptionalTypeFactory.forGuava())
      .serializeNulls()
      .create();

    String json = gson.toJson(new GuavaOptionalTester());
    GuavaOptionalTester deserialized = gson.fromJson(json, GuavaOptionalTester.class);
    assertThat(deserialized).isEqualTo(new GuavaOptionalTester(null, null));
  }

  @Test
  @DisplayName("No type - Typeless optional is not serialized by factory")
  void noTypeTypelessOptionalIsNotSerializedByFactory() {
    Optional optional = gson.fromJson("null", TYPELESS_OPTIONAL_TYPE.getType());
    assertThat(optional).isNull();
  }
}
