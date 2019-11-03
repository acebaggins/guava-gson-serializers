package com.baggonius.gson.immutable;

import com.baggonius.gson.common.JsonResources;
import com.baggonius.gson.common.TestObject;
import com.google.common.collect.ImmutableSortedSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.baggonius.gson.common.ImmutableDeserializerException;
import com.baggonius.gson.common.TestObjectNotComparable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("ImmutableSortedSet Deserializers")
class ImmutableSortedSetDeserializerTest {
  private static final TypeToken<ImmutableSortedSet<String>> SIMPLE_SET_TYPE = new TypeToken<ImmutableSortedSet<String>>() {};
  private static final TypeToken<ImmutableSortedSet<TestObject>> OBJECT_SET_TYPE = new TypeToken<ImmutableSortedSet<TestObject>>() {};
  private static final TypeToken<ImmutableSortedSet<TestObjectNotComparable>> NON_COMPARABLE_OBJECT_SET_TYPE = new TypeToken<ImmutableSortedSet<TestObjectNotComparable>>() {};

  private final Gson gson = ImmutableTypeAdapters.withImmutableCollectionSerializers(new GsonBuilder()).create();

  @Test
  @DisplayName("Reading unsorted simple array json sorts content")
  void immutableSortedSetReadingUnsortedSimpleJsonSortsContent() {
    ImmutableSortedSet<String> set = gson.fromJson(JsonResources.UNSORTED_SIMPLE_ARRAY, SIMPLE_SET_TYPE.getType());

    assertThat(set).isEqualTo(ImmutableSortedSet.of("a", "b", "c"));
  }

  @Test
  @DisplayName("Reading unsorted object array json sorts content")
  void immutableSortedSetReadingUnsortedObjectJsonSortsContent() {
    ImmutableSortedSet<TestObject> set = gson.fromJson(JsonResources.UNSORTED_OBJECT_ARRAY, OBJECT_SET_TYPE.getType());

    assertThat(set).isEqualTo(ImmutableSortedSet.of(TestObject.objectOne(), TestObject.objectTwo()));
  }

  @Test
  @DisplayName("Throw exception if type is not comparable")
  void immutableSortedSetThrowExceptionIfTypeIsNotComparable() {
    assertThatExceptionOfType(ImmutableDeserializerException.class)
      .isThrownBy(() -> gson.fromJson(JsonResources.UNSORTED_OBJECT_ARRAY, NON_COMPARABLE_OBJECT_SET_TYPE.getType()))
      .withCauseInstanceOf(ClassCastException.class);
  }
}
