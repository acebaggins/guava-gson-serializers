package com.baggonius.gson.immutable;


import com.baggonius.gson.common.JsonResources;
import com.baggonius.gson.common.TestObject;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ImmutableList Deserializers")
class ImmutableListDeserializerTest {
  private static final TypeToken<ImmutableList<String>> SIMPLE_LIST_TYPE = new TypeToken<ImmutableList<String>>() {};
  private static final TypeToken<ImmutableList<TestObject>> OBJECT_LIST_TYPE = new TypeToken<ImmutableList<TestObject>>() {};

  private final Gson gson = ImmutableTypeAdapters.withImmutableCollectionSerializers(new GsonBuilder()).create();

  @Test
  @DisplayName("Can read simple array json")
  void immutableListCanReadSimpleJson() {
    ImmutableList<String> list = gson.fromJson(JsonResources.SIMPLE_ARRAY, SIMPLE_LIST_TYPE.getType());

    assertThat(list)
      .isInstanceOf(ImmutableList.class)
      .isEqualTo(ImmutableList.of("a", "b", "c"));
  }

  @Test
  @DisplayName("Can read object array json")
  void immutableListCanReadObjectJson() {
    ImmutableList<TestObject> list = gson.fromJson(JsonResources.OBJECT_ARRAY, OBJECT_LIST_TYPE.getType());

    assertThat(list)
      .isInstanceOf(ImmutableList.class)
      .isEqualTo(ImmutableList.of(TestObject.objectOne(), TestObject.objectTwo()));
  }
}
