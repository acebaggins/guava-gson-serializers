package com.baggonius.gson.immutable;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.baggonius.gson.common.TestObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.baggonius.gson.common.JsonResources.OBJECT_MAP;
import static com.baggonius.gson.common.JsonResources.SIMPLE_MAP;
import static com.baggonius.gson.common.TestObject.objectOne;
import static com.baggonius.gson.common.TestObject.objectTwo;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ImmutableMap Deserializers")
class ImmutableMapDeserializerTest {
  private static final TypeToken<ImmutableMap<String, String>> SIMPLE_MAP_TYPE = new TypeToken<ImmutableMap<String, String>>() {};
  private static final TypeToken<ImmutableMap<String, TestObject>> OBJECT_MAP_TYPE = new TypeToken<ImmutableMap<String, TestObject>>() {};

  private final Gson gson = ImmutableTypeAdapters.withImmutableCollectionSerializers(new GsonBuilder()).create();

  @Test
  @DisplayName("Can read simple map json")
  void immutableMapCanReadSimpleJson() {
    ImmutableMap<String, String> map = gson.fromJson(SIMPLE_MAP, SIMPLE_MAP_TYPE.getType());

    assertThat(map)
      .isEqualTo(ImmutableMap.of("a", "value a", "b", "value b", "c", "value c"));
  }

  @Test
  @DisplayName("Can read object map json")
  void immutableMapCanReadObjectJson() {
    ImmutableMap<String, TestObject> map = gson.fromJson(OBJECT_MAP, OBJECT_MAP_TYPE.getType());

    assertThat(map).isEqualTo(ImmutableMap.of("a", objectOne(), "b", objectTwo()));

    assertThat(map.get("a")).isInstanceOf(TestObject.class);
    assertThat(map.get("b")).isInstanceOf(TestObject.class);
  }
}
