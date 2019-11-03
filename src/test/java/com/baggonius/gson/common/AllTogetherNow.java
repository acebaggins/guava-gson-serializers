package com.baggonius.gson.common;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "Guava"})
public class AllTogetherNow {
  private final ImmutableList<TestObject> list;
  private final ImmutableSet<TestObject> set;
  private final ImmutableSortedSet<TestObject> sortedSet;
  private final ImmutableBiMap<String, TestObject> biMap;
  private final ImmutableMap<String, TestObject> map;
  private final ImmutableSortedMap<String, TestObject> sortedMap;
  private final Optional<TestObject> optional;
  private final com.google.common.base.Optional<TestObject> guavaOptional;

  public AllTogetherNow() {
    this(newBuilder());
  }

  private AllTogetherNow(Builder builder) {
    list = builder.list;
    set = builder.set;
    sortedSet = builder.sortedSet;
    biMap = builder.biMap;
    map = builder.map;
    sortedMap = builder.sortedMap;
    optional = builder.optional;
    guavaOptional = builder.guavaOptional;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AllTogetherNow that = (AllTogetherNow) o;
    return Objects.equals(list, that.list) &&
      Objects.equals(set, that.set) &&
      Objects.equals(sortedSet, that.sortedSet) &&
      Objects.equals(biMap, that.biMap) &&
      Objects.equals(map, that.map) &&
      Objects.equals(sortedMap, that.sortedMap) &&
      Objects.equals(optional, that.optional) &&
      Objects.equals(guavaOptional, that.guavaOptional);
  }

  @Override
  public int hashCode() {
    return Objects.hash(list, set, sortedSet, biMap, map, sortedMap, optional, guavaOptional);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("list", list)
      .add("set", set)
      .add("sortedSet", sortedSet)
      .add("biMap", biMap)
      .add("map", map)
      .add("sortedMap", sortedMap)
      .add("optional", optional)
      .add("guavaOptional", guavaOptional)
      .toString();
  }

  public static class Builder {
    private ImmutableList<TestObject> list = ImmutableList.of();
    private ImmutableSet<TestObject> set = ImmutableSet.of();
    private ImmutableSortedSet<TestObject> sortedSet = ImmutableSortedSet.of();
    private ImmutableBiMap<String, TestObject> biMap = ImmutableBiMap.of();
    private ImmutableMap<String, TestObject> map = ImmutableMap.of();
    private ImmutableSortedMap<String, TestObject> sortedMap = ImmutableSortedMap.of();
    private Optional<TestObject> optional = Optional.empty();
    private com.google.common.base.Optional<TestObject> guavaOptional = com.google.common.base.Optional.absent();

    private Builder() {}

    public Builder withList(ImmutableList<TestObject> val) {
      list = val;
      return this;
    }

    public Builder withSet(ImmutableSet<TestObject> val) {
      set = val;
      return this;
    }

    public Builder withSortedSet(ImmutableSortedSet<TestObject> val) {
      sortedSet = val;
      return this;
    }

    public Builder withBiMap(ImmutableBiMap<String, TestObject> val) {
      biMap = val;
      return this;
    }

    public Builder withMap(ImmutableMap<String, TestObject> val) {
      map = val;
      return this;
    }

    public Builder withSortedMap(ImmutableSortedMap<String, TestObject> val) {
      sortedMap = val;
      return this;
    }

    public Builder withOptional(Optional<TestObject> val) {
      optional = val;
      return this;
    }

    public Builder withGuavaOptional(com.google.common.base.Optional<TestObject> val) {
      guavaOptional = val;
      return this;
    }

    public AllTogetherNow build() {
      return new AllTogetherNow(this);
    }
  }
}
