package com.baggonius.gson.immutable;

import com.google.common.collect.ImmutableSortedMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.baggonius.gson.common.JsonResources;
import com.baggonius.gson.common.TestObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.baggonius.gson.common.JsonResources.UNSORTED_OBJECT_MAP;
import static com.baggonius.gson.common.TestObject.objectOne;
import static com.baggonius.gson.common.TestObject.objectTwo;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ImmutableSortedMap Deserializers")
class ImmutableSortedMapDeserializerTest {
  private static final TypeToken<ImmutableSortedMap<String, String>> SIMPLE_MAP_TYPE = new TypeToken<ImmutableSortedMap<String, String>>() {};
  private static final TypeToken<ImmutableSortedMap<String, TestObject>> OBJECT_MAP_TYPE = new TypeToken<ImmutableSortedMap<String, TestObject>>() {};

  private final Gson gson = ImmutableTypeAdapters.withImmutableCollectionSerializers(new GsonBuilder()).create();

  @Test
  @DisplayName("Reading unsorted simple map json sorts content")
  void immutableSortedMapReadingUnsortedSimpleJsonSortsContent() {
    ImmutableSortedMap<String, String> map = gson.fromJson(JsonResources.UNSORTED_SIMPLE_MAP, SIMPLE_MAP_TYPE.getType());

    assertThat(map)
      .isEqualTo(ImmutableSortedMap.of("a", "value a", "b", "value b", "c", "value c"));
  }

  @Test
  @DisplayName("Reading unsorted object map json sorts content")
  void immutableSortedMapReadingUnsortedObjectJsonSortsContent() {
    ImmutableSortedMap<String, TestObject> map = gson.fromJson(UNSORTED_OBJECT_MAP, OBJECT_MAP_TYPE.getType());

    assertThat(map)
      .isEqualTo(ImmutableSortedMap.of("a", objectOne(), "b", objectTwo()));
  }
}
