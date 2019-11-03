package com.baggonius.gson.immutable;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.baggonius.gson.common.TestObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.baggonius.gson.common.JsonResources.OBJECT_ARRAY;
import static com.baggonius.gson.common.JsonResources.SIMPLE_ARRAY;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ImmutableSet Deserializers")
class ImmutableSetDeserializerTest {
  private static final TypeToken<ImmutableSet<String>> SIMPLE_SET_TYPE = new TypeToken<ImmutableSet<String>>() {};
  private static final TypeToken<ImmutableSet<TestObject>> OBJECT_SET_TYPE = new TypeToken<ImmutableSet<TestObject>>() {};

  private final Gson gson = ImmutableTypeAdapters.withImmutableCollectionSerializers(new GsonBuilder()).create();

  @Test
  @DisplayName("Can read simple array json")
  void immutableSetCanReadSimpleJson() {
    ImmutableSet<String> set = gson.fromJson(SIMPLE_ARRAY, SIMPLE_SET_TYPE.getType());

    assertThat(set)
      .isInstanceOf(ImmutableSet.class)
      .isEqualTo(ImmutableSet.of("a", "b", "c"));
  }

  @Test
  @DisplayName("Can read object array json")
  void immutableSetCanReadObjectJson() {
    ImmutableSet<TestObject> set = gson.fromJson(OBJECT_ARRAY, OBJECT_SET_TYPE.getType());

    assertThat(set)
      .isInstanceOf(ImmutableSet.class)
      .isEqualTo(ImmutableSet.of(TestObject.objectOne(), TestObject.objectTwo()));
  }
}
