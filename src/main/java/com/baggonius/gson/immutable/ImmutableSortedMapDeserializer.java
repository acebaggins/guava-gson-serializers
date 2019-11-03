package com.baggonius.gson.immutable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;

import java.util.Map;

public class ImmutableSortedMapDeserializer extends BaseMapDeserializer<ImmutableMap<?, ?>> {

  @Override
  protected ImmutableMap<?, ?> buildFrom(Map<?, ?> map) {
    return ImmutableSortedMap.copyOf(map);
  }

}
