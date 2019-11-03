package com.baggonius.gson;

import com.baggonius.gson.common.JsonResources;
import com.baggonius.gson.common.TestObject;
import com.baggonius.gson.immutable.ImmutableTypeAdapters;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.baggonius.gson.common.AllTogetherNow;
import com.baggonius.gson.optional.OptionalTypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"Guava", "deprecation"})
@DisplayName("All Together Now")
class AllTogetherNowTest {
  private final Gson gson = ImmutableTypeAdapters.withImmutableCollectionSerializers(new GsonBuilder())
    .registerTypeAdapterFactory(OptionalTypeFactory.forJDK())
    .registerTypeAdapterFactory(OptionalTypeFactory.forGuava())
    .create();

  @Test
  @DisplayName("Composite Object - Happy path")
  void compositeObjectHappyPath() {
    assertThat(gson.fromJson(JsonResources.ALL_TOGETHER_NOW, AllTogetherNow.class)).isEqualTo(expected());
  }

  @Test
  @DisplayName("Composite Object - Empty path")
  void compositeObjectEmptyPath() {
    assertThat(gson.fromJson("{}", AllTogetherNow.class)).isEqualTo(new AllTogetherNow());
  }

  private AllTogetherNow expected() {
    return AllTogetherNow.newBuilder()
      .withBiMap(ImmutableBiMap.of("a", TestObject.objectOne(), "b", TestObject.objectTwo()))
      .withGuavaOptional(com.google.common.base.Optional.of(TestObject.objectTwo()))
      .withMap(ImmutableMap.of("a", TestObject.objectOne(), "b", TestObject.objectTwo()))
      .withList(ImmutableList.of(TestObject.objectOne(), TestObject.objectTwo()))
      .withSet(ImmutableSet.of(TestObject.objectOne(), TestObject.objectTwo()))
      .withSortedSet(ImmutableSortedSet.of(TestObject.objectTwo(), TestObject.objectOne()))
      .withSortedMap(ImmutableSortedMap.of("a", TestObject.objectOne(), "b", TestObject.objectTwo()))
      .withOptional(Optional.of(TestObject.objectOne()))
      .build();
  }
}
