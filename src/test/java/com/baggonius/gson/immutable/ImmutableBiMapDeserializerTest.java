package com.baggonius.gson.immutable;

import com.baggonius.gson.common.JsonResources;
import com.baggonius.gson.common.TestObject;
import com.google.common.collect.ImmutableBiMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.baggonius.gson.common.ImmutableDeserializerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("ImmutableBiMap Deserializers")
class ImmutableBiMapDeserializerTest {
  private static final TypeToken<ImmutableBiMap<String, String>> SIMPLE_MAP_TYPE = new TypeToken<ImmutableBiMap<String, String>>() {};
  private static final TypeToken<ImmutableBiMap<String, TestObject>> OBJECT_MAP_TYPE = new TypeToken<ImmutableBiMap<String, TestObject>>() {};

  private final Gson gson = ImmutableTypeAdapters.withImmutableCollectionSerializers(new GsonBuilder()).create();

  @Test
  @DisplayName("Can read simple map json")
  void ImmutableBiMapCanReadSimpleJson() {
    ImmutableBiMap<String, String> map = gson.fromJson(JsonResources.SIMPLE_MAP, SIMPLE_MAP_TYPE.getType());

    assertThat(map)
      .isEqualTo(ImmutableBiMap.of("a", "value a", "b", "value b", "c", "value c"));
  }

  @Test
  @DisplayName("Can read object map json")
  void ImmutableBiMapCanReadObjectJson() {
    ImmutableBiMap<String, TestObject> map = gson.fromJson(JsonResources.OBJECT_MAP, OBJECT_MAP_TYPE.getType());

    assertThat(map).isEqualTo(ImmutableBiMap.of("a", TestObject.objectOne(), "b", TestObject.objectTwo()));

    assertThat(map.get("a")).isInstanceOf(TestObject.class);
    assertThat(map.get("b")).isInstanceOf(TestObject.class);
  }

  @Test
  @DisplayName("Throw exception if map is not a BiMap candidate")
  void ifGsonThrowsAnErrorCreatingTheMapThenCatchRethrowIt() {
    assertThatExceptionOfType(ImmutableDeserializerException.class)
      .isThrownBy(() -> gson.fromJson(JsonResources.BAD_BI_MAP, OBJECT_MAP_TYPE.getType()));
  }
}
